package application;

import java.io.IOException;

import com.kuka.connectivity.motionModel.smartServo.ISmartServoRuntime;
import com.kuka.connectivity.motionModel.smartServo.SmartServo;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.CartDOF;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianImpedanceControlMode;
import com.kuka.roboticsAPI.uiModel.ApplicationDialogType;
import com.kuka.task.ITaskLogger;

import iiwa_com.IiwaServer;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * The robotics Reha application runs the statemachines and the server.
 */
public class IiwaComApp extends RoboticsAPIApplication {

	// station setup automatically determined via Dependency Injection
	@Inject
	private LBR robot;
	@Inject
	@Named("Kugel")
	private Tool tool;
	@Inject
	private ITaskLogger logger;

	// server
	private IiwaServer server;
	// Servo control shall only be executed in one thread (here)
	private ISmartServoRuntime servoRuntime;

	@Override
	public void initialize() {
		// frames and tools
		tool.attachTo(robot.getFlange());
		// smart servoing
		SmartServo servoMotion = new SmartServo(robot.getCurrentJointPosition());
		// cartesian impedance control
		if (!SmartServo.validateForImpedanceMode(tool)) {
			logger.error("Validation of Torque Model failed");
		}
		CartesianImpedanceControlMode impControl = new CartesianImpedanceControlMode();
		impControl.parametrize(CartDOF.TRANSL).setStiffness(1000);
		impControl.parametrize(CartDOF.ROT).setStiffness(100);
		impControl.setNullSpaceStiffness(50);
		// activate motion
		robot.moveAsync(servoMotion.setMode(impControl));
		servoRuntime = servoMotion.getRuntime();
		// create the gRPC server
		server = new IiwaServer(robot, tool.getDefaultMotionFrame(), 30000);
	}

	@Override
	public void run() {
		try {
			server.start();
			// Keep alive until the user finishes the application
			getApplicationUI().displayModalDialog(
					ApplicationDialogType.INFORMATION,
					"Press ok to finish the application.", "OK");
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void dispose() {
		servoRuntime.stopMotion();
		server.stop();
		try {
			server.blockUntilShutdown();
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		}
	}
}