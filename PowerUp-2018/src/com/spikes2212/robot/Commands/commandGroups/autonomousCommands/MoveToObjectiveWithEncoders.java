package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTankWithPID;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveToObjectiveWithEncoders extends CommandGroup {

	// defining PID constants
	public static final Supplier<Double> TOLERANCE = ConstantHandler.addConstantDouble("Move To Objective- tolerance",
			10);
	public static final Supplier<Double> PID_WAIT_TIME = ConstantHandler
			.addConstantDouble("Move To Objective - pid wait time", 1);

	public static final Supplier<Double> DRIVING_KP = ConstantHandler.addConstantDouble("Move To Objective - driving kp",
			0.004);
	public static final Supplier<Double> DRIVING_KI = ConstantHandler.addConstantDouble("Move To Objective - driving ki",
			0.000025);
	public static final Supplier<Double> DRIVING_KD = ConstantHandler.addConstantDouble("Move To Objective - driving kd",
			0.0);

	public MoveToObjectiveWithEncoders(Supplier<Double> setPoint) {
		SubsystemComponents.Drivetrain.LEFT_ENCODER.reset();
		SubsystemComponents.Drivetrain.RIGHT_ENCODER.reset();
		PIDSettings settings = new PIDSettings(DRIVING_KP.get(), DRIVING_KI.get(), DRIVING_KD.get(), TOLERANCE.get(),
				PID_WAIT_TIME.get());
		addSequential(new DriveTankWithPID(Robot.drivetrain, SubsystemComponents.Drivetrain.LEFT_ENCODER,
				SubsystemComponents.Drivetrain.RIGHT_ENCODER, setPoint, settings));
	}
}
