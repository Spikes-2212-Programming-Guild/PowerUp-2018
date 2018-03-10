package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreScaleAuto extends CommandGroup {
	// defining PID set point of the point between switch and scale on the y
	// axis
	public static final Supplier<Double> BETWEEN_SWITCH_AND_SCALE = ConstantHandler
			.addConstantDouble("between objectives", 160);

	public ScoreScaleAuto() {
		addSequential(new MoveToSetpointWithEncoders(BETWEEN_SWITCH_AND_SCALE));
		
	}
}
