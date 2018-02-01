package com.spikes2212.robot.Commands.commandGroups;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystemWithTimeSinceReachingLimit;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceCube extends CommandGroup {

    public PlaceCube() {
        addSequential(new MoveBasicSubsystem(Robot.folder, SubsystemConstants.Folder.DOWN_SPEED));
        addSequential(new MoveBasicSubsystem(Robot.claw, SubsystemConstants.Claw.OPEN_SPEED));
        //addSequential(new MoveBasicSubsystemWithTimeSinceReachingLimit(Robot.roller, SubsystemConstants.Roller.ROLL_OUT_SPEED, SubsystemConstants.Roller.WAIT_TIME.get()));
        addSequential(new MoveBasicSubsystem(Robot.folder, SubsystemConstants.Folder.UP_SPEED));
        
    }
}
