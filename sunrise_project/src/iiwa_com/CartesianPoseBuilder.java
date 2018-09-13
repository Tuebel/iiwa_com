package iiwa_com;

import com.kuka.roboticsAPI.geometricModel.Frame;

import iiwa_com.IiwaMsgs.CartesianPose;

public class CartesianPoseBuilder {

	public static CartesianPose buildPose(Frame frame, String baseFrameId,
			String childFrameId) {
		return CartesianPose.newBuilder().setBaseFrame(baseFrameId)
				.setChildFrame(childFrameId).setY(frame.getY())
				.setZ(frame.getZ()).setA(frame.getAlphaRad())
				.setB(frame.getBetaRad()).setC(frame.getGammaRad()).build();
	}
}
