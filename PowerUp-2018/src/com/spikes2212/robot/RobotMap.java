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
		public static final int CLAW = 4;
	}

	public static interface PWM {
		public static final int FOLDER = 1;
		public static final int ROLLER = 6;
		public static final int LIFT_MOTOR_A = 8;
		public static final int LIFT_MOTOR_B = 9;
	}

	public static interface DIO {
		public static final int FOLDER_MAX_LIMIT = 2;
		public static final int FOLDER_MIN_LIMIT = 3;
		public static final int CLAW_LIMIT = 5;
		public static final int ROLLER_LIGHT_SENSOR = 7;
		public static final int LIFT_HALL_EFFECTS = 9;
		public static final int LIFT_LIMIT_UP = 10;
		public static final int LIFT_LIMIT_DOWN = 11;
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
