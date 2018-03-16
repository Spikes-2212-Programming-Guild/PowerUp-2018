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
public class MoveToSetpointWithEncoders extends CommandGroup {

	// setPoints
	public static final Supplier<Double> SWITCH_SET_POINT = ConstantHandler.addConstantDouble("switch set point", 135);
	public static final Supplier<Double> BETWEEN_SWITCH_AND_SCALE = ConstantHandler
			.addConstantDouble("between objectives", 240);
	public static final Supplier<Double> FAR_SCALE_SET_POINT = ConstantHandler.addConstantDouble("far scale set point",
			400);
	public static final Supplier<Double> SCORE_SCALE_WITH_TIME_SET_POINT = ConstantHandler
			.addConstantDouble("score scale with time set point", 190);

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

	public MoveToSetpointWithEncoders(Supplier<Double> setPoint) {
		PIDSettings settings = new PIDSettings(DRIVING_KP.get(), DRIVING_KI.get(), DRIVING_KD.get(), TOLERANCE.get(),
				PID_WAIT_TIME.get());
		addSequential(new DriveTankWithPID(Robot.drivetrain, SubsystemComponents.Drivetrain.LEFT_ENCODER,
				SubsystemComponents.Drivetrain.RIGHT_ENCODER, setPoint, settings));
	}
}
