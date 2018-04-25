package com.spikes2212.robot.simpleCommand;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.robot.SubsystemConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveLiftDown extends CommandGroup{
	public MoveLiftDown() {
		addSequential(new MoveBasicSubsystem(Robot.lift , SubsystemConstants.Lift.DOWN_SPEED_SUPPLIER));
	}
}
