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
		addParallel(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.UNLOCK_SPEED));
		addSequential(new MoveBasicSubsystem(Robot.lift, () -> {
			if (SubsystemComponents.LiftLocker.LIMIT_LOCKED.get())
				return 0.0;
			if (SubsystemComponents.LiftLocker.LIMIT_UNLOCKED.get())
				return speed.get();
			return SubsystemConstants.Lift.STAYING_SPEED.get();
		}));
	}
}
