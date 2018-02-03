package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.Commands.TurnToReflector;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScoreSwitchFromTheMiddle extends CommandGroup {

	public static final Supplier<Double> FORWARDS_SPEED = ConstantHandler
			.addConstantDouble("ScoreSwitchFromTheMiddle - forwards speed", 0.2);
	public static final Supplier<Double> ROTATION_SPEED = ConstantHandler
			.addConstantDouble("ScoreSwitchFromTheMiddle - rotation speed", 0.4);

	public ScoreSwitchFromTheMiddle() {
		addSequential(new TurnToReflector(Robot.drivetrain, FORWARDS_SPEED, ROTATION_SPEED));
	}
}
