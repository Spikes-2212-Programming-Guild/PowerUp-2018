package com.spikes2212.robot.simpleCommand;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TurnRight extends CommandGroup{
	public TurnRight() {
		addSequential(new DriveArcade(Robot.drivetrain,()-> 0.0, SubsystemConstants.Drivetrain.SIMPLE_TURN_RIGHT_SPEED));
	}
}
