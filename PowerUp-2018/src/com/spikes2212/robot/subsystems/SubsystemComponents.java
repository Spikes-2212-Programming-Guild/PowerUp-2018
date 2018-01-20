package com.spikes2212.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.spikes2212.robot.RobotMap;

public class SubsystemComponents {

	public static interface Climber {
		public static final WPI_TalonSRX CLIMBER_MOTOR = new WPI_TalonSRX(RobotMap.CAN.CLIMBER);
	}
}
