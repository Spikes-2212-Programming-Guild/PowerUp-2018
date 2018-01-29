package com.spikes2212.robot.Commands.commandGroups;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemConstants;
import com.spikes2212.robot.Commands.MoveLift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveAndLockLift extends CommandGroup {

	public MoveAndLockLift(Supplier<Double> speed) {
		addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.UNLOCK_SPEED));
		addSequential(new MoveLift( speed));
		addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.LOCK_SPEED));
	}
}
