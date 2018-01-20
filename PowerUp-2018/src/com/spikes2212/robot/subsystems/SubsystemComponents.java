package com.spikes2212.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.spikes2212.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;

public class SubsystemComponents {

	public static interface Climber {
		public static final WPI_TalonSRX CLIMBER_MOTOR = new WPI_TalonSRX(RobotMap.CAN.CLIMBER);
	}
	
	public static interface Folder{
		public static final VictorSP FOLDER_MOTOR = new VictorSP(RobotMap.PWM.FOLDER);
		public static final DigitalInput FOLDER_MAX_LIMIT = new DigitalInput(RobotMap.DIO.FOLDER_MAX_LIMIT);
		public static final DigitalInput FOLDER_MIN_LIMIT = new DigitalInput(RobotMap.DIO.FOLDER_MIN_LIMIT); 
	}
}
