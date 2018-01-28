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

	public MoveAndLockLift(boolean up) {
		addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.CLOSE_SPEED));
		addSequential(new MoveLift((up) ? SubsystemConstants.Lift.UP_SPEED : SubsystemConstants.Lift.DOWN_SPEED));
		addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.OPEN_SPEED));
	}
}
