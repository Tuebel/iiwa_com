package iiwa_com;

import iiwa_com.IiwaMsgs.CartesianForce;
import iiwa_com.IiwaMsgs.CartesianPose;
import iiwa_com.IiwaMsgs.WrapperMsg;

import java.io.IOException;
import java.net.Socket;

import com.google.protobuf.InvalidProtocolBufferException;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.task.ITaskLogger;

/**
 * Enables the communication to the robot via protocol buffers. Run it in a
 * separate thread so the it does not block the robot operation.
 * 
 * @author Tim Übelhör
 * 
 */
public class Communicator implements Runnable {
	// transport
	private final Socket socket;
	// robot state
	private final ITaskLogger logger;
	private final LBR robot;

	/**
	 * Create a connection that operates on the given socket.
	 * 
	 * @param socket
	 */
	public Communicator(Socket socket, ITaskLogger logger, LBR robot) {
		this.socket = socket;
		this.logger = logger;
		this.robot = robot;
	}

	@Override
	public void finalize() {
		try {
			close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	public void close() throws IOException {
		if (!socket.isClosed()) {
			socket.close();
		}
	}

	@Override
	public void run() {
		while (socket.isConnected()) {
			try {
				WrapperMsg msg = WrapperMsg.parseDelimitedFrom(socket
						.getInputStream());
				switch (msg.getMsgCase()) {
				case CARTESIAN_FORCE_REQUEST:
					logger.info("streaming force");
					streamForce();
					break;
				case CARTESIAN_POSE_REQUEST:
					logger.info("streaming pose");
					streamPose();
					break;
				}

			} catch (InvalidProtocolBufferException e) {
				logger.error(e.getMessage());
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
	}

	private void streamForce() {
		while (socket.isConnected()) {
			// Build the force message in the flange frame
			CartesianForce msg = CartesianForceBuilder.buildForce(robot
					.getFlange().getName(), robot.getExternalForceTorque(robot
					.getFlange()));
			try {
				msg.writeDelimitedTo(socket.getOutputStream());
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
	}

	private void streamPose() {
		while (socket.isConnected()) {
			// Build the pose message as pose of the flange in the world
			CartesianPose msg = CartesianPoseBuilder.buildPose(robot
					.getCurrentCartesianPosition(robot.getFlange()));
			try {
				msg.writeDelimitedTo(socket.getOutputStream());
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
	}
}
