package com.spikes2212.robot.simpleCommand;

import com.spikes2212.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TurnRight extends CommandGroup{
	public TurnRight() {
		addSequential(new TurnWithIMU(Robot.drivetrain, ()->-TurnWithIMU.ROTATE_SPEED.get(), -90.0, TurnWithIMU.ROTATE_TOLERANCE.get()));
	}
}
