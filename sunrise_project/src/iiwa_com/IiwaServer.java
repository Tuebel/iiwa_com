package iiwa_com;

import java.io.IOException;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.Frame;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;

import iiwa_com.IiwaServiceOuterClass.CartesianPose;
import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
 * Implements server capabilities for the IiwaService
 * 
 * @author Tim Übelhör
 * 
 */
public class IiwaServer {

	private final Server server;

	public IiwaServer(LBR robot, ObjectFrame tcpFrame, int port) {
		// build the server
		server = ServerBuilder.forPort(port)
				.addService(new IiwaService(robot, tcpFrame)).build();
	}

	public void start() throws IOException {
		server.start();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				// Use stderr here since the logger may has been reset by its
				// JVM shutdown hook.
				System.err
						.println("*** shutting down gRPC server since JVM is shutting down");
				IiwaServer.this.stop();
				System.err.println("*** server shut down");
			}
		});

	}

	public void stop() {
		if (server != null) {
			server.shutdown();
		}
	}

	/**
	 * Await termination on the main thread since the grpc library uses daemon
	 * threads.
	 */
	public void blockUntilShutdown() throws InterruptedException {
		if (server != null) {

		}
	}

	/**
	 * Implementation of the grpc service
	 * 
	 * @author Tim Übelhör
	 * 
	 */
	private static class IiwaService extends
			IiwaServiceGrpc.IiwaServiceImplBase {

		private final LBR robot;
		private final ObjectFrame tcpFrame;
		// partialy built pose stores the frame information
		private final CartesianPose posePrototype;

		/**
		 * Create the service which reads the information from the robot.
		 * 
		 * @param robot
		 *            Read the state of this robot.
		 * @param tcpFrame
		 *            Read the pose of this frame and the force in this frame.
		 */
		public IiwaService(LBR robot, ObjectFrame tcpFrame) {
			this.robot = robot;
			this.tcpFrame = tcpFrame;
			posePrototype = CartesianPose.newBuilder().setBaseFrame("world")
					.setChildFrame(tcpFrame.toString()).buildPartial();
		}

		@Override
		public void streamCartesianPose(
				iiwa_com.IiwaServiceOuterClass.StreamCartesianPoseRequest request,
				io.grpc.stub.StreamObserver<iiwa_com.IiwaServiceOuterClass.CartesianPose> responseObserver) {
			// run until killed
			while (true) {
				// read from robot
				Frame p = robot.getCurrentCartesianPosition(tcpFrame);
				// build and send the message
				responseObserver.onNext(CartesianPose.newBuilder(posePrototype)
						.setX(p.getX()).setY(p.getY()).setZ(p.getZ())
						.setA(p.getAlphaRad()).setB(p.getBetaRad())
						.setC(p.getGammaRad()).build());
			}
		}

		@Override
		public void streamCartesianState(
				iiwa_com.IiwaServiceOuterClass.StreamCartesianStateRequest request,
				io.grpc.stub.StreamObserver<iiwa_com.IiwaServiceOuterClass.CartesianState> responseObserver) {
			throw new NotImplementedException();
		}
	}
}
