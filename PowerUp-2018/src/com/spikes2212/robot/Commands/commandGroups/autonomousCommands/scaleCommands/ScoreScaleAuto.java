package com.spikes2212.robot.Commands.commandGroups.autonomousCommands.scaleCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemConstants;
import com.spikes2212.robot.Commands.commandGroups.MoveLift;
import com.spikes2212.robot.Commands.commandGroups.PlaceCube;
import com.spikes2212.robot.Commands.commandGroups.autonomousCommands.MoveToSetpointWithEncoders;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * puts a cube in the scale, no matter which side, and then move the lift down
 * and get ready for teleop
 *
 */
public class ScoreScaleAuto extends CommandGroup {

	// defining PID set point of the point between switch and scale on the y
	// axis
	public static final Supplier<Double> BETWEEN_SWITCH_AND_SCALE = ConstantHandler
			.addConstantDouble("between objectives", 240);

	// defining 90 degrees rotations data
	public static final Supplier<Double> TURNING_SPEED = ConstantHandler
			.addConstantDouble("score scale  -  turning speed", 0.5);
	public static final Supplier<Double> TURNING_TIME_OUT = ConstantHandler
			.addConstantDouble("score scale - turning timeout", 1.2);

	// defining far and close scale set points
	public static final Supplier<Double> CLOSE_SCALE_X_SET_POINT = ConstantHandler
			.addConstantDouble("close scale x setpoint", 40);
	public static final Supplier<Double> FAR_SCALE_X_SET_POINT = ConstantHandler
			.addConstantDouble("close scale x setpoint", 400);

	// defining moving forward data
	public static final Supplier<Double> FORWARD_SPEED = ConstantHandler
			.addConstantDouble("score scale  - forward speed", 0.4);
	public static final Supplier<Double> FORWARD_TIME_OUT = ConstantHandler
			.addConstantDouble("score scale - forward timeout", 1);

	public ScoreScaleAuto(String gameData, char startSide) {
		// driving to the scale set point
		addSequential(new MoveToSetpointWithEncoders(BETWEEN_SWITCH_AND_SCALE));
		// rotate 90 degrees
		addSequential(new DriveArcade(Robot.drivetrain, () -> 0.0,
				() -> startSide == 'L' ? -TURNING_SPEED.get() : TURNING_SPEED.get()), TURNING_TIME_OUT.get());
		
		// drive to the correct x position of the scale
		addSequential(new MoveToSetpointWithEncoders(
				gameData.charAt(1) == startSide ? CLOSE_SCALE_X_SET_POINT : FAR_SCALE_X_SET_POINT));

		// rotate -90 degrees
		addSequential(new DriveArcade(Robot.drivetrain, () -> 0.0,
				() -> startSide == 'L' ? TURNING_SPEED.get() : -TURNING_SPEED.get()), TURNING_TIME_OUT.get());

		// move lift up
		addSequential(new MoveLift(SubsystemConstants.Lift.UP_SPEED));
		addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.LOCK_SPEED));

		// drive a little bit straight
		addSequential(new DriveArcade(Robot.drivetrain, FORWARD_SPEED, () -> 0.0), FORWARD_TIME_OUT.get());

		// put cube
		addSequential(new PlaceCube());

		// drive backwards
		addSequential(new DriveArcade(Robot.drivetrain, () -> -FORWARD_SPEED.get(), () -> 0.0), FORWARD_TIME_OUT.get());

		// close lift
		addSequential(new MoveLift(SubsystemConstants.Lift.DOWN_SPEED_SUPPLIER));
	}

}
