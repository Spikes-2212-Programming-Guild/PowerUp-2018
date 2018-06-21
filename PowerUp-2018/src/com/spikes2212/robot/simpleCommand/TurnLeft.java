package com.spikes2212.robot.simpleCommand;

import com.spikes2212.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TurnLeft extends CommandGroup {
	public TurnLeft(double angle) {
		addSequential(new TurnWithIMU(Robot.drivetrain, TurnWithIMU.ROTATE_SPEED,angle , TurnWithIMU.ROTATE_TOLERANCE.get()));
	}
}
