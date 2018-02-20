package com.spikes2212.robot.Commands.commandGroups;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystemWithTimeSinceReachingLimit;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class UnlockLiftLocker extends CommandGroup {

    public UnlockLiftLocker() {
      addParallel(new MoveBasicSubsystem(Robot.lift, 0));
      addSequential(new MoveBasicSubsystemWithTimeSinceReachingLimit(Robot.liftLocker, SubsystemConstants.LiftLocker.UNLOCK_SPEED, 0.2));
    }
}
