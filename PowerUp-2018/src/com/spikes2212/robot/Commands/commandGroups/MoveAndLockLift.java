package com.spikes2212.robot.Commands.commandGroups;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveAndLockLift extends CommandGroup {

	public MoveAndLockLift(Supplier<Double> speed) {
		addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.UNLOCK_SPEED));
		addSequential(new MoveBasicSubsystem(Robot.lift, speed));
		addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.LOCK_SPEED));
	}
}
