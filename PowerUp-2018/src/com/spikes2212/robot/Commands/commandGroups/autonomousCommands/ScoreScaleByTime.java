package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemConstants;
import com.spikes2212.robot.Commands.commandGroups.MoveLift;
import com.spikes2212.robot.Commands.commandGroups.PlaceCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreScaleByTime extends CommandGroup {
	// defining PID set point of the point between switch and scale on the y
	// axis
	public static final Supplier<Double> BETWEEN_SWITCH_AND_SCALE = ConstantHandler
			.addConstantDouble("between objectives", 190);

	/**
	 * the scale is in the close side
	 */
	public static interface CloseScale {
		// defining turning constants
		public static final Supplier<Double> TURNING_SPEED = ConstantHandler
				.addConstantDouble("score scale  - close turning speed", 0.6);
		public static final Supplier<Double> TURNING_TIME_OUT = ConstantHandler
				.addConstantDouble("score scale - close turning timeout", 0.5);

		// defining forward constants
		public static final Supplier<Double> FORWARD_SPEED = ConstantHandler
				.addConstantDouble("score scale  - close forward speed", 0.4);
		public static final Supplier<Double> FORWARD_TIME_OUT = ConstantHandler
				.addConstantDouble("score scale - close forward timeout", 1.5);
	}

	/**
	 * the scale is in the far side
	 */
	public static interface FarScale {
		// defining turning constants
		public static final Supplier<Double> TURNING_SPEED = ConstantHandler
				.addConstantDouble("score scale  - far turning speed", 0.6);
		public static final Supplier<Double> TURNING_TIME_OUT = ConstantHandler
				.addConstantDouble("score scale - far turning timeout", 1.3);
	}

	public ScoreScaleByTime(String gameData, char startSide) {

		// driving to the correct scale set point
		addSequential(new MoveToSetpointWithEncoders(BETWEEN_SWITCH_AND_SCALE));

		// the scale is in the close side
		if (gameData.charAt(1) == startSide) {
			// turning towards the scale
			addSequential(
					new DriveArcade(Robot.drivetrain, () -> 0.0,
							() -> startSide == 'L' ? -CloseScale.TURNING_SPEED.get() : CloseScale.TURNING_SPEED.get()),
					CloseScale.TURNING_TIME_OUT.get());

			// move lift up
			addSequential(new MoveLift(SubsystemConstants.Lift.UP_SPEED));
			addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.LOCK_SPEED));
			// drive closer to scale
			addSequential(new DriveArcade(Robot.drivetrain, CloseScale.FORWARD_SPEED, () -> 0.0),
					CloseScale.FORWARD_TIME_OUT.get());

			// put cube
			addSequential(new PlaceCube());

			// drive backwards
			addSequential(new DriveArcade(Robot.drivetrain, () -> -CloseScale.FORWARD_SPEED.get(), () -> 0.0),
					CloseScale.FORWARD_TIME_OUT.get());

			// close lift
			addSequential(new MoveLift(SubsystemConstants.Lift.DOWN_SPEED_SUPPLIER));
			addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.LOCK_SPEED));

		}

		// the scale is in the far side
		else {
			// rotate 90 degrees
			addSequential(
					new DriveArcade(Robot.drivetrain, () -> 0.0,
							() -> startSide == 'L' ? -FarScale.TURNING_SPEED.get() : FarScale.TURNING_SPEED.get()),
					FarScale.TURNING_TIME_OUT.get());
		}
	}
}