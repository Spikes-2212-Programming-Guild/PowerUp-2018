package com.spikes2212.robot.Commands.commandGroups;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystemWithTimeSinceReachingLimit;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.robot.SubsystemConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * This command group picks up the cube that is infront of it.
 * 
 * @author Matan
 */
public class PickUpCube extends CommandGroup {

	public PickUpCube() {
		addParallel(new MoveBasicSubsystem(Robot.folder, SubsystemConstants.Folder.DOWN_SPEED_SUPPLIER));
		// vacuums the cube
		addSequential(new MoveBasicSubsystemWithTimeSinceReachingLimit(Robot.roller, SubsystemConstants.Roller.ROLL_IN_SPEED, 0.5));
		// moving the folder up so we can lift the cube safely with out the
		// robot falling
		addSequential(new MoveBasicSubsystem(Robot.folder, SubsystemConstants.Folder.UP_SPEED));
	}
}
