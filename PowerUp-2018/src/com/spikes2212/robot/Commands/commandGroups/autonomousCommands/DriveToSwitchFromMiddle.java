package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcadeWithPID;
import com.spikes2212.robot.ImageProcessingConstants;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemConstants;
import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveToSwitchFromMiddle extends CommandGroup {
	// initial turning constants
	public static final Supplier<Double> FORWARD_SPEED = ConstantHandler
			.addConstantDouble("switch from middle auto - forward speed", 0.4);
	public static final Supplier<Double> ROTATION_SPEED = ConstantHandler
			.addConstantDouble("switch from middle auto - rotation speed", 0.6);
	public static final Supplier<Double> ROTATION_TIME = ConstantHandler
			.addConstantDouble("switch from middle auto  - rotation time", 0.5);

	// orienting constants
	public static final Supplier<Double> TOLERANCE = ConstantHandler
			.addConstantDouble("switch from middle auto - orienting tolerance", 0);
	public static final Supplier<Double> PID_WAIT_TIME = ConstantHandler
			.addConstantDouble("switch from middle auto PID waitTime", 1);
	public static final Supplier<Double> ORIENTATION_FORWARD_SPEED = ConstantHandler
			.addConstantDouble("switch from middle auto - orientation forward speed", 0.45);
	public static final Supplier<Double> ORIENTATION_TIME_OUT = ConstantHandler
			.addConstantDouble("switch from middle auto - oriantation timeout", 3.8);

	public DriveToSwitchFromMiddle(String gameData) {
		// turn to the correct direction
		addSequential(
				new DriveArcade(Robot.drivetrain, FORWARD_SPEED,
						() -> gameData.charAt(0) == 'L' ? ROTATION_SPEED.get() : -ROTATION_SPEED.get()),
				ROTATION_TIME.get());

		// drive while orienting
		addSequential(new DriveArcadeWithPID(Robot.drivetrain, ImageProcessingConstants.CENTER, () -> 0.0,
				ORIENTATION_FORWARD_SPEED,
				new PIDSettings(SubsystemConstants.Drivetrain.ORIENTATION_KP.get(),
						SubsystemConstants.Drivetrain.ORIENTATION_KI.get(),
						SubsystemConstants.Drivetrain.ORIENTATION_KD.get(), TOLERANCE.get(), PID_WAIT_TIME.get()),
				ImageProcessingConstants.RANGE), ORIENTATION_TIME_OUT.get());
	}
}