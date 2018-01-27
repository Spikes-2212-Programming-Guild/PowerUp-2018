package com.spikes2212.robot;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

public class SubsystemConstants {
	public static interface Climber {
		public static final Supplier<Double> MAX_VOLTAGE = ConstantHandler.addConstantDouble("Climber Max Voltage", 25);
		
		public static final Supplier<Double> SPEED = ConstantHandler.addConstantDouble("Climber Speed", 0.9);
	}
	
	public static interface Claw {
		public static final Supplier<Double> MAX_VOLTAGE = ConstantHandler.addConstantDouble("Claw Max Voltage", 10);
		
		public static final Supplier<Double> OPEN_SPEED = ConstantHandler.addConstantDouble("Claw Open Speed", 0.5);
		public static final Supplier<Double> CLOSE_SPEED = ConstantHandler.addConstantDouble("Claw Close Speed", 0.6);
	}
	
	public static interface Folder{
		public static final Supplier<Double> UP_SPEED = ConstantHandler.addConstantDouble("Folder Up Speed", 0.75);
		public static final Supplier<Double> DOWN_SPEED = ConstantHandler.addConstantDouble("Folder Down Speed", 0.4);
	}
}
