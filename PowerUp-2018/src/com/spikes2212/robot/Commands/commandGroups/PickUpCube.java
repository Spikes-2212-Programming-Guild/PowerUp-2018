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
    	addSequential(new MoveBasicSubsystem(Robot.roller, SubsystemConstants.Roller.TAKE_SPEED));
    	addSequential(new MoveBasicSubsystem(Robot.claw, SubsystemConstants.Claw.CLOSE_SPEED));
    	addSequential(new MoveBasicSubsystem(Robot.folder, SubsystemConstants.Folder.UP_SPEED));
    }
}
