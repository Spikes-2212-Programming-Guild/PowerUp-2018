package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcadeWithPID;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.robot.SubsystemConstants;
import com.spikes2212.robot.Commands.commandGroups.PlaceCube;
import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PutCubeInScale extends CommandGroup {

	public static final Supplier<Double> FORWARD_SPEED = ConstantHandler
			.addConstantDouble("ScoreScaleFromSide - Forward Speed", 0.4);
	public static final Supplier<Double> ROTATE_SPEED = ConstantHandler
			.addConstantDouble("ScoreScaleFromSide - Rotate Speed", 0.4);
	public static final Supplier<Double> MOVING_KP = ConstantHandler.addConstantDouble("ScoreScaleFromSide - Moving kp",
			0.7);
	public static final Supplier<Double> MOVING_KI = ConstantHandler.addConstantDouble("ScoreScaleFromSide - Moving ki",
			0.04);
	public static final Supplier<Double> MOVING_KD = ConstantHandler.addConstantDouble("ScoreScaleFromSide - Moving kd",
			0.1);
	public static final Supplier<Double> TOLERANCE = ConstantHandler.addConstantDouble("ScoreScaleFromSide - Tolerance",
			0.5);
	public static final Supplier<Double> WAIT_TIME = ConstantHandler.addConstantDouble("ScoreScaleFromSide - Wait Time",
			0.5);
	public static final Supplier<Double> DISTANCE_FROM_SCALE = ConstantHandler
			.addConstantDouble("ScoreScaleFromSide - Distance From Scale", 20);

	public PutCubeInScale() {
		addSequential(new DriveArcadeWithPID(Robot.drivetrain, SubsystemComponents.Drivetrain.LEFT_ENCODER,
				DISTANCE_FROM_SCALE, FORWARD_SPEED,
				new PIDSettings(MOVING_KP.get(), MOVING_KI.get(), MOVING_KD.get(), TOLERANCE.get(), WAIT_TIME.get()),
				2.0));
		addSequential(new DriveArcade(Robot.drivetrain, ()->0.0, ROTATE_SPEED));
		addSequential(new MoveBasicSubsystem(Robot.lift, SubsystemConstants.Lift.UP_SPEED));
		addSequential(new PlaceCube());
		addSequential(new MoveBasicSubsystem(Robot.lift, SubsystemConstants.Lift.DOWN_SPEED));
	}
}
