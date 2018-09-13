package iiwa_com;

import com.kuka.roboticsAPI.geometricModel.Frame;

import iiwa_com.IiwaMsgs.CartesianPose;

public class CartesianPoseBuilder {

	public static CartesianPose buildPose(Frame frame, String baseFrameId,
			String childFrameId) {
		// KUKA default units are mm!
		return CartesianPose.newBuilder().setBaseFrame(baseFrameId)
				.setChildFrame(childFrameId).setX(frame.getX() / 1000)
				.setY(frame.getY() / 1000).setZ(frame.getZ() / 1000)
				.setA(frame.getAlphaRad()).setB(frame.getBetaRad())
				.setC(frame.getGammaRad()).build();
	}
}
