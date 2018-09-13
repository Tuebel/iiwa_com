package iiwa_com;

import iiwa_com.IiwaMsgs.CartesianForce;

import com.kuka.roboticsAPI.sensorModel.ForceSensorData;

public class CartesianForceBuilder {
	public static CartesianForce buildForce(String frameId,
			ForceSensorData force) {
		return CartesianForce.newBuilder().setFrame(frameId)
				.setFX(force.getForce().getX()).setFY(force.getForce().getY())
				.setFZ(force.getForce().getZ()).setTX(force.getTorque().getX())
				.setTY(force.getTorque().getY())
				.setTZ(force.getTorque().getZ()).build();
	}
}
