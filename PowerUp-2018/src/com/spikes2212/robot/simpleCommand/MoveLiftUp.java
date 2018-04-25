package com.spikes2212.robot.simpleCommand;

import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.robot.Commands.commandGroups.MoveLiftToTarget;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveLiftUp extends CommandGroup{
	public MoveLiftUp() {
		addSequential(new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.SWITCH));
	}
}
