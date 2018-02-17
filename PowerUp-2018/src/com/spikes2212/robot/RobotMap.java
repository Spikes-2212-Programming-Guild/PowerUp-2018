package com.spikes2212.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static interface CAN {
		public static final int CLIMBER = 0;
		public static final int DRIVE_RIGHT1 = 2;
		public static final int DRIVE_RIGHT2 = 3;
		public static final int DRIVE_LEFT1 = 4;
		public static final int DRIVE_LEFT2 = 5;
		public static final int LIFT_MOTOR_A = 1;
		public static final int LIFT_MOTOR_B = 9;
	}

	public static interface PWM {
		public static final int FOLDER_1 = 0;
		public static final int FOLDER_2 = 1;
		public static final int LIFT_LOCKER = 2;
		public static final int ROLLER_LEFT = 5;
		public static final int ROLLER_RIGHT = 4;

	}

	public static interface DIO {
		public static final int FOLDER_MAX_LIMIT = 5;
		public static final int FOLDER_MIN_LIMIT = 4;

		public static final int DRIVE_RIGHT_ENCODER_A = 10;
		public static final int DRIVE_RIGHT_ENCODER_B = 11;
		public static final int DRIVE_LEFT_ENCODER_A = 12;
		public static final int DRIVE_LEFT_ENCODER_B = 13;

		public static final int LIFT_LOCKER_UNLOCKED_LIMIT = 0;
		public static final int LIFT_LOCKER_LOCKED_LIMIT = 1;

		public static final int LIFT_HALL_EFFECTS_SWITCH = 21;
		public static final int LIFT_HALL_EFFECTS_LOW_SCALE = 22;
		public static final int LIFT_HALL_EFFECTS_MID_SCALE = 23;

		public static final int LIFT_LIMIT_UP = 2;
		public static final int LIFT_LIMIT_DOWN = 3;
	}

	public static interface ANALOG_IN {
		public static final int ROLLER_LASER_SENSOR = 0;
	}

	public static interface USB {
		public static final int REAR_CAMERA = 0;
		public static final int FRONT_CAMERA = 1;
	}
}
