package com.spikes2212.robot.Commands.commandGroups;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class KeepLockOpen extends CommandGroup {

    public KeepLockOpen() {
       addParallel(new MoveBasicSubsystem(Robot.lift, 0.0));
       addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.UNLOCK_SPEED));
       addParallel(new MoveBasicSubsystem(Robot.liftLocker, 0.0));
    }
}
