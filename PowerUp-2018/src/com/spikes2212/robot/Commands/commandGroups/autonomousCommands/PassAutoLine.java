package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PassAutoLine extends CommandGroup {
	public static final Supplier<Double> FORWARD_SPEED = ConstantHandler
			.addConstantDouble("pass auto line- forward speed", 0.5);
	public static final Supplier<Double> TIME_OUT = ConstantHandler.addConstantDouble("pass auto line- time out", 3.5);

	public PassAutoLine() {
		addSequential(new DriveArcade(Robot.drivetrain, FORWARD_SPEED, () -> 0.0), TIME_OUT.get());
	}

}
