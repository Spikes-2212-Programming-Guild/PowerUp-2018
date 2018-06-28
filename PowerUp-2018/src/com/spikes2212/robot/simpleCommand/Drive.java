package com.spikes2212.robot.simpleCommand;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;
import com.spikes2212.robot.Robot;

public class Drive extends DriveTank {
	public Drive(double speedLeft, double speedRight, double time) {
		super(Robot.drivetrain, speedLeft, speedRight);
		setTimeout(time);
	}

}
