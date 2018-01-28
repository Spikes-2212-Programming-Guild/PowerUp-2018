package com.spikes2212.robot.Commands.commandGroups;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickUpCube extends CommandGroup {

    public PickUpCube() {
    	addParallel(new MoveBasicSubsystem(Robot.folder, SubsystemConstants.Folder.DOWN_SPEED));
    	addSequential(new MoveBasicSubsystem(Robot.claw, SubsystemConstants.Claw.OPEN_SPEED));
    	addSequential(new MoveBasicSubsystem(Robot.roller, SubsystemConstants.Roller.IN_SPEED));
    	addSequential(new MoveBasicSubsystem(Robot.claw, SubsystemConstants.Claw.CLOSE_SPEED));
    }
}
