package com.spikes2212.robot.Commands.commandGroups;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class KeepLockerOpen extends CommandGroup {
	public KeepLockerOpen() {
		addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.UNLOCK_SPEED));
		addSequential(new MoveLift(() -> -0.1));
		addParallel(new MoveBasicSubsystem(Robot.liftLocker, 0.0));
	}
}
