package com.spikes2212.robot;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

public class SubsystemConstants {

	public static interface Drivetrain {
		public static final Supplier<Double> ORIENTATION_KP = ConstantHandler
				.addConstantDouble("drivetrain - oriantation kp", 0.7);
		public static final Supplier<Double> ORIENTATION_KI = ConstantHandler
				.addConstantDouble("drivetrain - oriantation ki", 0.01);
		public static final Supplier<Double> ORIENTATION_KD = ConstantHandler
				.addConstantDouble("drivetrain - oriantation kd", 0.1);
	}

	public static interface Folder {
		public static final Supplier<Double> UP_SPEED = ConstantHandler.addConstantDouble("Folder Up Speed", 0.3);
		public static final Supplier<Double> FIRST_DOWN_SPEED = ConstantHandler
				.addConstantDouble("Folder pulse Down Speed", -0.6);
		public static final Supplier<Double> STAYING_SPEED = ConstantHandler.addConstantDouble("Folder Staying speed",
				-0.18);
		public static final Supplier<Double> DOWN_SPEED_SUPPLIER = () -> SubsystemComponents.Folder.MIN_LIMIT.get()
				? STAYING_SPEED.get() : FIRST_DOWN_SPEED.get();
		public static final Supplier<Double> UP_WAIT_TIME = ConstantHandler.addConstantDouble("folder up waitTime", 1);
	}

	public static interface Roller {
		public static final Supplier<Double> ROLL_IN_SPEED = ConstantHandler.addConstantDouble("Roller In Speed", -0.9);
		public static final Supplier<Double> FAST_ROLL_OUT_SPEED = ConstantHandler.addConstantDouble("Roller fast Out Speed",
				0.7);
		public static final Supplier<Double> SLOW_ROLL_OUT_SPEED = ConstantHandler.addConstantDouble("Roller slow Out Speed",
				0.4);
		public static final Supplier<Integer> LASER_SENSOR_CONSTANT = ConstantHandler.addConstantInt("laser sensor",
				27);
		// TODO find out the real distance of the cube from the sensor
		public static final Supplier<Double> CUBE_DISTANCE = ConstantHandler.addConstantDouble("Cube Distance", 5);
	}

	public static interface Climber {
		public static final Supplier<Double> UP_SPEED = ConstantHandler.addConstantDouble("climber up speed", 0.7);
		public static final Supplier<Double> STALL_SPEED = ConstantHandler.addConstantDouble("climbre stall speed",
				0.65);
		public static final Supplier<Double> DOWN_SPEED = ConstantHandler.addConstantDouble("climber down speed", -0.2);
	}

	public static interface LiftLocker {
		public static final Supplier<Double> LOCK_SPEED = ConstantHandler.addConstantDouble("LiftLocker Close Speed",
				-0.5);
		public static final Supplier<Double> UNLOCK_SPEED = ConstantHandler.addConstantDouble("LiftLocker Open Speed",
				0.5);
	}

	public static interface Lift {
		public static final Supplier<Double> UP_SPEED = ConstantHandler.addConstantDouble("Lift Up Speed", 0.9);
		public static final Supplier<Double> STAYING_SPEED = ConstantHandler.addConstantDouble("Lift Staying Speed",
				0.1);
		public static final Supplier<Double> FIRST_DOWN_SPEED = ConstantHandler
				.addConstantDouble("Lift First Down Speed", -0.2);
		public static final Supplier<Double> SECOND_DOWN_SPEED = ConstantHandler
				.addConstantDouble("Lift Second Down Speed", -0.2);
		public static final Supplier<Double> DOWN_SPEED_SUPPLIER = () -> SubsystemComponents.Lift
				.getPosition() < SubsystemComponents.Lift.HallEffects.SWITCH.getIndex() ? SECOND_DOWN_SPEED.get()
						: FIRST_DOWN_SPEED.get();

	}

}
