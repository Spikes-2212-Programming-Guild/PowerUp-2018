package com.spikes2212.robot.Commands.commandGroups;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This command group prepares the robot so it will be ready to pick up a cube at any time
 *@author Matan
 */
public class PrepareToPickUp extends CommandGroup {

    public PrepareToPickUp() {
    	//moving the lift down to the ground in the level of the cubes
    	addSequential(new MoveLift(SubsystemConstants.Lift.DOWN_SPEED));
    	//moving the folder down so it can hold a cube
    	addSequential(new MoveBasicSubsystem(Robot.folder, SubsystemConstants.Folder.DOWN_SPEED));
    }
}
