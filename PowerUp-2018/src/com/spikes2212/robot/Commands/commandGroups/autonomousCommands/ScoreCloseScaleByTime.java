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

public class ScoreCloseScaleByTime extends CommandGroup {
	// defining PID set point of the point between switch and scale on the y
	// axis
	public static final Supplier<Double> BETWEEN_SWITCH_AND_SCALE = ConstantHandler
			.addConstantDouble("between objectives", 190);

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

	/**
	 * This command group score a power cube to the scale responding to the
	 * starting side. This command should be run only if the starting side is
	 * the same side as the side of the scale
	 * 
	 * @param gameData-
	 *            the data from the field
	 * @param startSide-
	 *            the side which the robot starts at
	 */
	public ScoreCloseScaleByTime(char startSide) {

		// driving to the correct scale set point
		addSequential(new MoveToSetpointWithEncoders(BETWEEN_SWITCH_AND_SCALE));

		// turning towards the scale
		addSequential(new DriveArcade(Robot.drivetrain, () -> 0.0,
				() -> startSide == 'L' ? -TURNING_SPEED.get() : TURNING_SPEED.get()), TURNING_TIME_OUT.get());

		// move lift up
		addSequential(new MoveLift(SubsystemConstants.Lift.UP_SPEED));
		addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.LOCK_SPEED));

		// drive closer to scale
		addSequential(new DriveArcade(Robot.drivetrain, FORWARD_SPEED, () -> 0.0), FORWARD_TIME_OUT.get());

		// put cube
		addSequential(new PlaceCube(SubsystemConstants.Roller.SLOW_ROLL_OUT_SPEED));

		// drive backwards
		addSequential(new DriveArcade(Robot.drivetrain, () -> -FORWARD_SPEED.get(), () -> 0.0), FORWARD_TIME_OUT.get());

		// close lift
		addSequential(new MoveLift(SubsystemConstants.Lift.DOWN_SPEED_SUPPLIER));
		addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.LOCK_SPEED));

	}
}