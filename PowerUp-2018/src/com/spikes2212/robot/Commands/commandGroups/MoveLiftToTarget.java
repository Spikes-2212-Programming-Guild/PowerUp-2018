package com.spikes2212.robot.Commands.commandGroups;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystemToTarget;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.robot.SubsystemConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveLiftToTarget extends CommandGroup {

	public MoveLiftToTarget(SubsystemComponents.Lift.HallEffects hallEffect) {
		addParallel(new MoveBasicSubsystemToTarget(Robot.lift,
				() -> SubsystemComponents.Lift.getPosition() > hallEffect.getIndex()
						? SubsystemConstants.Lift.DOWN_SPEED.get() : SubsystemConstants.Lift.UP_SPEED.get(),
				() -> !hallEffect.getHallEffect().get()));
		addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.UNLOCK_SPEED));

	}
}
