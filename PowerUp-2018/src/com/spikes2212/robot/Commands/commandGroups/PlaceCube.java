package com.spikes2212.robot.Commands.commandGroups;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystemToTarget;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.robot.SubsystemConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceCube extends CommandGroup {

	public PlaceCube() {
		addParallel(new MoveBasicSubsystem(Robot.folder, () -> SubsystemComponents.Folder.MIN_LIMIT.get()
				? SubsystemConstants.Folder.STAYING_SPEED.get() : SubsystemConstants.Folder.DOWN_SPEED.get()));
		addSequential(new MoveBasicSubsystemToTarget(Robot.roller, SubsystemConstants.Roller.ROLL_OUT_SPEED,
				() -> SubsystemComponents.Roller.hasCube()));

	}
}
