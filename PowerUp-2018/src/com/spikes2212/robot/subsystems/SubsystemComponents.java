package com.spikes2212.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.spikes2212.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;

public class SubsystemComponents {

	public static interface Climber {
		public static final WPI_TalonSRX MOTOR = new WPI_TalonSRX(RobotMap.CAN.CLIMBER);
	}
	
	public static interface Folder{
		public static final VictorSP MOTOR = new VictorSP(RobotMap.PWM.FOLDER);
		public static final DigitalInput MAX_LIMIT = new DigitalInput(RobotMap.DIO.FOLDER_MAX_LIMIT);
		public static final DigitalInput MIN_LIMIT = new DigitalInput(RobotMap.DIO.FOLDER_MIN_LIMIT); 
	}
	
	public static interface Claw{
		public static final WPI_TalonSRX MOTOR = new WPI_TalonSRX(RobotMap.CAN.CLAW);
		public static final DigitalInput LIMIT = new DigitalInput(RobotMap.DIO.CLAW_LIMIT);
	}
}
