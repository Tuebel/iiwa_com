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
	// constants
	private final String WORLD_FRAME = "world";
	private final String FLANGE_FRAME = "flange";
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
			logger.info("closed communicator on " + socket.getInetAddress());
		}
	}

	@Override
	public void run() {
		logger.info("listening for messages on " + socket.getInetAddress());
		while (!socket.isClosed() && socket.isConnected()) {
			try {
				if (socket.getInputStream().available() > 0) {
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
					case CARTESIAN_STATE_REQUEST:
						logger.info("cartesian state streaming is not implemented yet");
						break;
					default:
						logger.error("Unsupported message type");
					}
				}
			} catch (InvalidProtocolBufferException e) {
				logger.error(e.getMessage());
			} catch (IOException e) {
				logger.error(e.getMessage());
				break;
			}
		}
		close();
	}

	private void streamForce() {
		while (!socket.isClosed() && socket.isConnected()) {
			// Build the force message in the flange frame
			CartesianForce force = CartesianForceBuilder.buildForce(
					FLANGE_FRAME,
					robot.getExternalForceTorque(robot.getFlange()));
			WrapperMsg msg = WrapperMsg.newBuilder().setCartesianForce(force)
					.build();
			try {
				msg.writeDelimitedTo(socket.getOutputStream());
			} catch (IOException e) {
				logger.error(e.getMessage());
				break;
			}
		}
	}

	private void streamPose() {
		while (!socket.isClosed() && socket.isConnected()) {
			// Build the pose message as pose of the flange in the world
			CartesianPose pose = CartesianPoseBuilder.buildPose(
					robot.getCurrentCartesianPosition(robot.getFlange()),
					WORLD_FRAME, FLANGE_FRAME);
			WrapperMsg msg = WrapperMsg.newBuilder().setCartesianPose(pose)
					.build();
			try {
				msg.writeDelimitedTo(socket.getOutputStream());
			} catch (IOException e) {
				logger.error(e.getMessage());
				break;
			}
		}
	}
}
