package com.spikes2212.robot.Commands.commandGroups;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystemToTarget;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveLiftToTargetAndLock extends CommandGroup {

    public MoveLiftToTargetAndLock(Supplier<Double> liftSpeed, Supplier<Boolean> liftTarget) {
      addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.UNLOCK_SPEED));
      addSequential(new MoveBasicSubsystemToTarget(Robot.lift,liftSpeed,liftTarget));
      addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.LOCK_SPEED));
    }
}
