package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MiddleToSwitchThenBackAuto extends CommandGroup {
	public static Supplier<Double> BACK_SPEED = ConstantHandler.addConstantDouble("middle then back - back speed",
			-0.3);
	public static Supplier<Double> BACK_TIME = ConstantHandler.addConstantDouble("middle then back - back time", 0.8);

	public MiddleToSwitchThenBackAuto(String gameData) {
		// score in switch
		addSequential(new MiddleToSwitchAuto(gameData));
		// drive back
		addSequential(new DriveArcade(Robot.drivetrain, BACK_SPEED, () -> 0.0), BACK_TIME.get());

	}

}
