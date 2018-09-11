package application;

import java.io.IOException;

import com.kuka.connectivity.motionModel.smartServo.ISmartServoRuntime;
import com.kuka.connectivity.motionModel.smartServo.SmartServo;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.CartDOF;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.motionModel.IMotionContainer;
import com.kuka.roboticsAPI.motionModel.PositionHold;
import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianImpedanceControlMode;
import com.kuka.roboticsAPI.persistenceModel.processDataModel.IProcessData;
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
	@Inject
	@Named("PORT")
	private IProcessData port;

	// server
	private IiwaServer server;

	@Override
	public void initialize() {
		// frames and tools
		tool.attachTo(robot.getFlange());
		// cartesian impedance control
		if (!SmartServo.validateForImpedanceMode(tool)) {
			logger.error("Validation of Torque Model failed");
		}
		CartesianImpedanceControlMode impControl = new CartesianImpedanceControlMode();
		impControl.parametrize(CartDOF.TRANSL).setStiffness(50);
		impControl.parametrize(CartDOF.ROT).setStiffness(5);
		impControl.setNullSpaceStiffness(100);
		// move without timeout
		robot.moveAsync(new PositionHold(impControl, -1, null));
		// create the gRPC server
		server = new IiwaServer(robot, robot.getFlange(),
				(Integer) port.getValue());
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
		if (server != null) {
			server.stop();
			try {
				server.blockUntilShutdown();
			} catch (InterruptedException e) {
				logger.error(e.getMessage());
			}
		}
	}
}