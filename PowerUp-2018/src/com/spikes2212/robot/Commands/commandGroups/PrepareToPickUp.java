package com.spikes2212.robot.Commands.commandGroups;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PrepareToPickUp extends CommandGroup {

    public PrepareToPickUp() {
    	addSequential(new MoveAndLockLift(SubsystemConstants.Lift.DOWN_SPEED));
    	addSequential(new MoveBasicSubsystem(Robot.folder, SubsystemConstants.Folder.DOWN_SPEED));
    	addSequential(new MoveBasicSubsystem(Robot.claw, SubsystemConstants.Claw.OPEN_SPEED));
    }
}
