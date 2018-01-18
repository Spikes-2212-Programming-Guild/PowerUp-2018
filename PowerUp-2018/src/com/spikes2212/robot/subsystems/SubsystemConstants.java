package com.spikes2212.robot.subsystems;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

public class SubsystemConstants {
	public static final Supplier<Double> CLIMBER_MAX_VOLTAGE = ConstantHandler.addConstantDouble("Climber Max Voltage", 25);
}
