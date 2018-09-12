package application;

import iiwa_com.TcpServer;

import java.io.IOException;

import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.CartDOF;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.motionModel.PositionHold;
import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianImpedanceControlMode;
import com.kuka.roboticsAPI.persistenceModel.processDataModel.IProcessData;
import com.kuka.roboticsAPI.uiModel.ApplicationDialogType;
import com.kuka.task.ITaskLogger;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * 
 * @author Tim Übelhör
 * 
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
	private TcpServer server;

	@Override
	public void initialize() {
		// frames and tools
		tool.attachTo(robot.getFlange());
		// cartesian impedance control
		CartesianImpedanceControlMode impControl = new CartesianImpedanceControlMode();
		impControl.parametrize(CartDOF.TRANSL).setStiffness(100);
		impControl.parametrize(CartDOF.ROT).setStiffness(2);
		impControl.setNullSpaceStiffness(100);
		// move without timeout
		robot.moveAsync(new PositionHold(impControl, -1, null));
		// create the server
		try {
			server = new TcpServer((Integer) port.getValue(), logger, robot);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void run() {
		if (server != null) {
			Thread serverThread = new Thread(server);
			serverThread.start();
		}
		// Keep alive until the user finishes the application
		getApplicationUI().displayModalDialog(
				ApplicationDialogType.INFORMATION,
				"Press ok to finish the application.", "OK");
	}

	@Override
	public void dispose() {
		if (server != null) {
			try {
				server.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
	}
}