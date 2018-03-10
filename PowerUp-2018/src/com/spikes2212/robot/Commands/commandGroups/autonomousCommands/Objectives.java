package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

public interface Objectives {
	
	// defining PID set point of the switch
	public static final Supplier<Double> SET_POINT = ConstantHandler
			.addConstantDouble("move to switch - switch set point", 135);
}
