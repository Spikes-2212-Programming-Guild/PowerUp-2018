/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

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
		public static final int CLAW = 1;
		public static final int DRIVE_RIGHT1 = 2;
		public static final int DRIVE_RIGHT2 = 3;
		public static final int DRIVE_LEFT1 = 4;
		public static final int DRIVE_LEFT2 = 5;
	}

	public static interface PWM {

		public static final int FOLDER_1 = 0;
		public static final int FOLDER_2 = 1;
		public static final int ROLLER_RIGHT = 2;
		public static final int ROLLER_LEFT = 3;
		public static final int LIFT_LOCKER = 4;
		public static final int LIFT_MOTOR_A = 5;
		public static final int LIFT_MOTOR_B = 6;
	}

	public static interface DIO {
		public static final int FOLDER_MAX_LIMIT = 4;
		public static final int FOLDER_MIN_LIMIT = 5;
		public static final int CLAW_LIMIT = 3;
		public static final int DRIVE_RIGHT_ENCODER_A = 9;
		public static final int DRIVE_RIGHT_ENCODER_B = 12;
		public static final int DRIVE_LEFT_ENCODER_A = 10;
		public static final int DRIVE_LEFT_ENCODER_B = 11;
		public static final int LIFT_LOCKER_UP_LIMIT = 8;
		public static final int LIFT_LOCKER_DOWN_LIMIT = 2;
		public static final int LIFT_HALL_EFFECTS_SWITCH = 18;
		public static final int LIFT_HALL_EFFECTS_MID_SCALE = 6;
		public static final int LIFT_HALL_EFFECTS_LOW_SCALE = 7;
		public static final int LIFT_LIMIT_UP = 0;
		public static final int LIFT_LIMIT_DOWN = 1;
	}
	
	public static interface ANALOG_IN{
		public static final int ROLLER_LASER_SENSOR = 0;
  }
	
	public static interface USB {
		public static final int REAR_CAMERA = 0;
		public static final int FRONT_CAMERA = 1;
	}
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
