package com.spikes2212.robot.Commands.commandGroups;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.robot.SubsystemConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * This command group picks up the cube that is infront of it.
 * @author Matan
 */
public class PickUpCube extends CommandGroup {

	public PickUpCube() {
		addSequential(new MoveBasicSubsystem(Robot.folder, SubsystemConstants.Folder.DOWN_SPEED));
		addParallel(new MoveBasicSubsystem(Robot.folder, SubsystemConstants.Folder.STAYING_SPEED));
		//vacuums the cube
		addSequential(new MoveBasicSubsystem(Robot.roller, SubsystemConstants.Roller.ROLL_IN_SPEED));
		//moving the folder up so we can lift the cube safely with out the robot falling
	}
}
 