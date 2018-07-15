package com.spikes2212.robot.simpleCommand;

import com.spikes2212.robot.Commands.commandGroups.PlaceCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Blue extends CommandGroup {

	public Blue() {

		addSequential(new Drive(0.5, 0.5, 1));
		addSequential(new TurnLeft(25));
		addSequential(new Drive(0.5, 0.3, 0.2));
		addSequential(new TurnRight(120));
		addSequential(new PlaceCube());
	}
}
