package com.spikes2212.robot;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

public class SubsystemConstants {
	public static interface Climber {
		public static final Supplier<Double> MAX_VOLTAGE = ConstantHandler.addConstantDouble("Climber Max Voltage", 25);
	}
	
	public static interface Claw {
		public static final Supplier<Double> MAX_VOLTAGE = ConstantHandler.addConstantDouble("Claw Max Voltage", 10);
	}
}
