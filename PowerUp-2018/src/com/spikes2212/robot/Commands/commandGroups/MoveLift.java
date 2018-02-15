package com.spikes2212.robot.Commands.commandGroups;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.robot.SubsystemConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveLift extends CommandGroup {

	public MoveLift(Supplier<Double> speed) {
		addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.UNLOCK_SPEED));
		addParallel(new MoveBasicSubsystem(Robot.liftLocker, () -> SubsystemComponents.Lift.LIMIT_UP.get() ? SubsystemConstants.LiftLocker.LOCK_SPEED.get() : 0.0));
		addSequential(new MoveBasicSubsystem(Robot.lift, speed));
	}
}
