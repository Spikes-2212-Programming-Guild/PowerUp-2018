package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTankWithPID;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveToSwitchWithEncoders extends CommandGroup {

	// defining PID set point of the switch
	public static final Supplier<Double> SET_POINT = ConstantHandler
			.addConstantDouble("move to switch - switch set point", 135);

	// defining PID constants
	public static final Supplier<Double> TOLERANCE = ConstantHandler.addConstantDouble("move to switch - tolerance",
			10);
	public static final Supplier<Double> PID_WAIT_TIME = ConstantHandler
			.addConstantDouble("move to switch - pid wait time", 1);

	public static final Supplier<Double> DRIVING_KP = ConstantHandler.addConstantDouble("move to switch - driving kp",
			0.004);
	public static final Supplier<Double> DRIVING_KI = ConstantHandler.addConstantDouble("move to switch - driving ki",
			0.000025);
	public static final Supplier<Double> DRIVING_KD = ConstantHandler.addConstantDouble("move to switch - driving kd",
			0.0);

	public MoveToSwitchWithEncoders() {
		addSequential(new DriveTank(Robot.drivetrain,()->0.5,()->0.5));
	}
}
