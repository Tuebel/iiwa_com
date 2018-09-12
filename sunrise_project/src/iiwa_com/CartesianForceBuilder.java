package iiwa_com;

import iiwa_com.IiwaMsgs.CartesianForce;

import com.kuka.roboticsAPI.sensorModel.ForceSensorData;

public class CartesianForceBuilder {
	public static CartesianForce buildForce(String frameName,
			ForceSensorData force) {
		return CartesianForce.newBuilder().setFrame(frameName)
				.setFX(force.getForce().getX()).setFY(force.getForce().getY())
				.setFZ(force.getForce().getZ()).setTX(force.getTorque().getX())
				.setTY(force.getTorque().getY())
				.setTZ(force.getTorque().getZ()).build();
	}
}
