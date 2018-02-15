package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PutCubeInScale extends CommandGroup {

	public static final Supplier<Double> FORWARD_SPEED = ConstantHandler
			.addConstantDouble("ScoreScaleFromSide - Forward Speed", 0.4);
	public static final Supplier<Double> MOVING_KP = ConstantHandler.addConstantDouble("ScoreScaleFromSide - Moving kp",
			0.1);
	public static final Supplier<Double> MOVING_KI = ConstantHandler.addConstantDouble("ScoreScaleFromSide - Moving ki",
			0.1);
	public static final Supplier<Double> MOVING_KD = ConstantHandler.addConstantDouble("ScoreScaleFromSide - Moving kd",
			0.1);

	public PutCubeInScale() {

	}
}
