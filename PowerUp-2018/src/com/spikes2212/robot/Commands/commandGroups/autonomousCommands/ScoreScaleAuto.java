package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.Commands.commandGroups.PlaceCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreScaleAuto extends CommandGroup {
	// defining PID set point of the point between switch and scale on the y
	// axis
	public static final Supplier<Double> BETWEEN_SWITCH_AND_SCALE = ConstantHandler
			.addConstantDouble("between objectives", 160);

	/**
	 * the scale is in the close side
	 */
	public static interface CloseScale {
		// defining turning constants
		public static final Supplier<Double> TURNING_SPEED = ConstantHandler
				.addConstantDouble("score scale  - close turning speed", 0.5);
		public static final Supplier<Double> TURNING_TIME_OUT = ConstantHandler
				.addConstantDouble("score scale - close turning timeout", 0.6);
		/*
		 * public static final Supplier<Double> ANGLE = ConstantHandler
		 * .addConstantDouble("score scale - close angle", 45);
		 */

		// defining forward constants
		public static final Supplier<Double> FORWARD_SPEED = ConstantHandler
				.addConstantDouble("score scale  - close forward speed", 0.4);
		public static final Supplier<Double> FORWARD_TIME_OUT = ConstantHandler
				.addConstantDouble("score scale - close forward timeout", 1);
	}

	/**
	 * the scale is in the far side
	 */
	public static interface FarScale {
		// defining turning constants
		public static final Supplier<Double> TURNING_SPEED = ConstantHandler
				.addConstantDouble("score scale  - far turning speed", 0.5);
		public static final Supplier<Double> TURNING_TIME_OUT = ConstantHandler
				.addConstantDouble("score scale - far turning timeout", 1.2);
		/*
		 * public static final Supplier<Double> ANGLE = ConstantHandler
		 * .addConstantDouble("score scale - close angle", 90);
		 */

		// defining driving forward constants
		public static final Supplier<Double> FORWARD_SPEED = ConstantHandler
				.addConstantDouble("score scale  - far forward speed", 0.6);
		public static final Supplier<Double> FORWARD_TIME_OUT = ConstantHandler
				.addConstantDouble("score scale - far forward timeout", 5);
	}

	public ScoreScaleAuto(String gameData, char startSide) {

		// driving to the correct scale set point
		addSequential(new MoveToSetpointWithEncoders(BETWEEN_SWITCH_AND_SCALE));

		// the scale is in the close side
		if (gameData.charAt(1) == startSide) {
			// turning towards the scale
			addSequential(
					new DriveArcade(Robot.drivetrain, () -> 0.0,
							() -> startSide == 'L' ? -CloseScale.TURNING_SPEED.get() : CloseScale.TURNING_SPEED.get()),
					CloseScale.TURNING_TIME_OUT.get());

			// drive closer to scale
			addSequential(new DriveArcade(Robot.drivetrain, CloseScale.FORWARD_SPEED, () -> 0.0),
					CloseScale.FORWARD_TIME_OUT.get());

			// put cube
			addSequential(new PlaceCube());
		}

		// the scale is in the far side
		else {
			// rotate 90 degrees
			addSequential(
					new DriveArcade(Robot.drivetrain, () -> 0.0,
							() -> startSide == 'L' ? -FarScale.TURNING_SPEED.get() : FarScale.TURNING_SPEED.get()),
					FarScale.TURNING_TIME_OUT.get());
			// drive towards the other side of the field
			addSequential(new DriveArcade(Robot.drivetrain, FarScale.FORWARD_SPEED, () -> 0.0),
					FarScale.FORWARD_TIME_OUT.get());
		}
	}
}
