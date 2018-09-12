package iiwa_com;

import com.kuka.roboticsAPI.geometricModel.Frame;

import iiwa_com.IiwaMsgs.CartesianPose;

public class CartesianPoseBuilder {

	public static CartesianPose buildPose(Frame frame) {
		return CartesianPose.newBuilder()
				.setBaseFrame(frame.getParent().getName())
				.setChildFrame(frame.getName()).setX(frame.getX())
				.setY(frame.getY()).setZ(frame.getZ()).setA(frame.getAlphaRad())
				.setB(frame.getBetaRad()).setC(frame.getGammaRad()).build();
	}
}
