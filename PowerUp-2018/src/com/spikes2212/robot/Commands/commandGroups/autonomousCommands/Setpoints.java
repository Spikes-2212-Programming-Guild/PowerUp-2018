package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

public interface Setpoints {

	// defining PID set point of the switch
	public static final Supplier<Double> SWITCH_FROM_WALL = ConstantHandler
			.addConstantDouble("move to switch - switch set point", 135);

	//
	public static final Supplier<Double> BETWEEN_SWITCH_AND_SCALE = ConstantHandler
			.addConstantDouble("between objectives", 160);
}
