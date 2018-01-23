package com.spikes2212.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.spikes2212.utils.DoubleSpeedcontroller;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;

public class SubsystemComponents {

	public static interface Climber {
		public static final WPI_TalonSRX MOTOR = new WPI_TalonSRX(RobotMap.CAN.CLIMBER);
	}

	public static interface Folder {
		public static final VictorSP MOTOR = new VictorSP(RobotMap.PWM.FOLDER);
		public static final DigitalInput MAX_LIMIT = new DigitalInput(RobotMap.DIO.FOLDER_MAX_LIMIT);
		public static final DigitalInput MIN_LIMIT = new DigitalInput(RobotMap.DIO.FOLDER_MIN_LIMIT);
	}

	public static interface Claw {
		public static final WPI_TalonSRX MOTOR = new WPI_TalonSRX(RobotMap.CAN.CLAW);
		public static final DigitalInput LIMIT = new DigitalInput(RobotMap.DIO.CLAW_LIMIT);
	}

	public static interface Roller {
		public static final VictorSP MOTOR = new VictorSP(RobotMap.PWM.ROLLER);
		public static final DigitalInput LIGHT_SENSOR = new DigitalInput(RobotMap.DIO.ROLLER_LIGHT_SENSOR);
	}

	public static interface Lift {
		public static final DoubleSpeedcontroller MOTOR = new DoubleSpeedcontroller(new VictorSP(RobotMap.PWM.LIFT_MOTOR_A), new VictorSP(RobotMap.PWM.LIFT_MOTOR_B));
		public static final DigitalInput HALL_EFFECTS_SWITCH = new DigitalInput(RobotMap.DIO.LIFT_HALL_EFFECTS_SWITCH);
		public static final DigitalInput HALL_EFFECTS_MID_SCALE = new DigitalInput(RobotMap.DIO.LIFT_HALL_EFFECTS_MID_SCALE);
		public static final DigitalInput HALL_EFFECTS_LOW_SCALE = new DigitalInput(RobotMap.DIO.LIFT_HALL_EFFECTS_LOW_SCALE);
		public static final DigitalInput LIMIT_UP = new DigitalInput(RobotMap.DIO.LIFT_LIMIT_UP);
		public static final DigitalInput LIMIT_DOWN = new DigitalInput(RobotMap.DIO.LIFT_LIMIT_DOWN);
	}
}
