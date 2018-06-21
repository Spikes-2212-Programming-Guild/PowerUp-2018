package com.spikes2212.robot.simpleCommand;

import com.spikes2212.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TurnRight extends CommandGroup{
	public TurnRight(double angle) {
		addSequential(new TurnWithIMU(Robot.drivetrain, ()->-TurnWithIMU.ROTATE_SPEED.get(), -angle, TurnWithIMU.ROTATE_TOLERANCE.get()));
	}
}
