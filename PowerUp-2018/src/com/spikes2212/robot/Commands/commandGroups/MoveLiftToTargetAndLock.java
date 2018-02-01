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

    public MoveLiftToTargetAndLock(Supplier<Double> speed, Supplier<Boolean> onTarget) {
      addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.UNLOCK_SPEED));
      addSequential(new MoveBasicSubsystemToTarget(Robot.lift,speed,onTarget));
      addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.LOCK_SPEED));
    }
}
