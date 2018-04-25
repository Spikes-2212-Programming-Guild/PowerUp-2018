package com.spikes2212.robot.simpleCommand;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Move extends CommandGroup {
	public Move(double time) {
		addSequential(new DriveArcade(Robot.drivetrain, SubsystemConstants.Drivetrain.SIMPLE_SPEED, () -> 0.0), time);
	}
	
}
