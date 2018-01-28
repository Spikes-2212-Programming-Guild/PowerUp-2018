package com.spikes2212.robot.Commands.commandGroups;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemConstants;
import com.spikes2212.robot.Commands.MoveLift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveAndLockLift extends CommandGroup {

	//The variable moveUp tells the command group if the lift should move up or down
	public MoveAndLockLift(boolean moveUp) {
		addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.UNLOCK_SPEED));
		addSequential(new MoveLift((moveUp) ? SubsystemConstants.Lift.UP_SPEED : SubsystemConstants.Lift.DOWN_SPEED));
		addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.LOCK_SPEED));
	}
}
