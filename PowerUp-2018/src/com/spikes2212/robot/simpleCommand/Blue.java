package com.spikes2212.robot.simpleCommand;

import com.spikes2212.robot.Commands.commandGroups.PlaceCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Blue extends CommandGroup {

	public Blue() {

		addSequential(new Drive(0.25, 0.25, 1));
		addSequential(new TurnLeft(50));
		addSequential(new Drive(0.25, 0.15, 0.5));
		addSequential(new TurnRight(95));
		addSequential(new PlaceCube());

	}
}
