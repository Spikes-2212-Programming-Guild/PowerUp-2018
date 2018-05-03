package com.spikes2212.robot.simpleCommand;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.robot.SubsystemConstants;
import com.spikes2212.robot.Commands.commandGroups.MoveLift;
import com.spikes2212.robot.Commands.commandGroups.MoveLiftToTarget;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveLiftDown extends CommandGroup{
	public MoveLiftDown() {
		addSequential(new MoveLift(SubsystemConstants.Lift.DOWN_SPEED_SUPPLIER));
	}
}
