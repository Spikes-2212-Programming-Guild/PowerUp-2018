package com.spikes2212.robot;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

public class SubsystemConstants {
	// TODO real numbers
	public static interface Climber {
		public static final Supplier<Double> MAX_VOLTAGE = ConstantHandler.addConstantDouble("Climber Max Voltage", 25);

		public static final Supplier<Double> SPEED = ConstantHandler.addConstantDouble("Climber Speed", 0.9);
	}

	public static interface Claw {
		public static final Supplier<Double> MAX_VOLTAGE = ConstantHandler.addConstantDouble("Claw Max Voltage", 25);

		public static final Supplier<Double> OPEN_SPEED = ConstantHandler.addConstantDouble("Claw Open Speed", 0.5);
		public static final Supplier<Double> CLOSE_SPEED = ConstantHandler.addConstantDouble("Claw Close Speed", -0.6);
	}

	public static interface Folder {
		public static final Supplier<Double> UP_SPEED = ConstantHandler.addConstantDouble("Folder Up Speed", 0.75);
		public static final Supplier<Double> DOWN_SPEED = ConstantHandler.addConstantDouble("Folder Down Speed", -0.4);
	}

	public static interface Roller {
		public static final Supplier<Double> ROLL_IN_SPEED = ConstantHandler.addConstantDouble("Roller In Speed", -0.6);
		public static final Supplier<Double> ROLL_OUT_SPEED = ConstantHandler.addConstantDouble("Roller Out Speed", 0.7);
		public static final Supplier<Integer> LASER_SENSOR_CONSTANT = ConstantHandler.addConstantInt("laser sensor", 27);
		//TODO find out the real distance of the cube from the sensor 
		public static final Supplier<Double> CUBE_DISTANCE = ConstantHandler.addConstantDouble("Cube Distance", 20);
	}

	public static interface LiftLocker {
		public static final Supplier<Double> LOCK_SPEED = ConstantHandler.addConstantDouble("LiftLocker Close Speed",
				-0.5);
		public static final Supplier<Double> UNLOCK_SPEED = ConstantHandler.addConstantDouble("LiftLocker Open Speed",
				0.5);
	}

	public static interface Lift {
		public static final Supplier<Double> UP_SPEED = ConstantHandler.addConstantDouble("Lift Up Speed", 0.7);
		public static final Supplier<Double> DOWN_SPEED = ConstantHandler.addConstantDouble("Lift Down Speed", -0.6);
	}
}
