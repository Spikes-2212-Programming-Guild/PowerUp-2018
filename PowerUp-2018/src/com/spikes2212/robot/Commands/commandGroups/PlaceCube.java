package com.spikes2212.robot.Commands.commandGroups;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceCube extends CommandGroup {

	public PlaceCube() {
		addParallel(new MoveBasicSubsystem(Robot.folder, SubsystemConstants.Folder.DOWN_SPEED_SUPPLIER));
		addSequential(new MoveBasicSubsystem(Robot.roller, SubsystemConstants.Roller.ROLL_OUT_SPEED),
				SubsystemConstants.Roller.ROLL_OUT_TIME.get());

	}
}
