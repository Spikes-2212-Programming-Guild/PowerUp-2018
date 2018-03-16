package com.spikes2212.robot.Commands.commandGroups.autonomousCommands.scaleCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemConstants;
import com.spikes2212.robot.Commands.TurnWithEncoders;
import com.spikes2212.robot.Commands.commandGroups.MoveLift;
import com.spikes2212.robot.Commands.commandGroups.PlaceCube;
import com.spikes2212.robot.Commands.commandGroups.autonomousCommands.MoveToSetpointWithEncoders;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreCloseScale extends CommandGroup {

	// defining forward constants
	public static final Supplier<Double> SLOW_DRIVING_SPEED = ConstantHandler
			.addConstantDouble("score close scale  - slow driving speed", 0.4);
	public static final Supplier<Double> SLOW_DRIVING_TIME_OUT = ConstantHandler
			.addConstantDouble("score close scale  - slow driving timeout", 1.5);
	
	public static final double TURNING_ANGLE = 45;

	/**
	 * Score to the close scale and drives backwards. This should only run if
	 * the start side is the same as the scale side
	 * 
	 * @param startSide
	 */
	public ScoreCloseScale(char startSide) {

		// driving to the scale set point
		addSequential(new MoveToSetpointWithEncoders(MoveToSetpointWithEncoders.BETWEEN_SWITCH_AND_SCALE));

		// rotate 45 degrees
		addSequential(new TurnWithEncoders(startSide == 'L' ? -TURNING_ANGLE : TURNING_ANGLE));

		// move lift up
		addSequential(new MoveLift(SubsystemConstants.Lift.UP_SPEED));
		addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.LOCK_SPEED));

		// drive a little bit straight
		addSequential(new DriveArcade(Robot.drivetrain, SLOW_DRIVING_SPEED, () -> 0.0), SLOW_DRIVING_TIME_OUT.get());

		// put cube
		addSequential(new PlaceCube());

		// drive backwards
		addSequential(new DriveArcade(Robot.drivetrain, () -> -SLOW_DRIVING_SPEED.get(), () -> 0.0),
				SLOW_DRIVING_TIME_OUT.get());

		// close lift
		addSequential(new MoveLift(SubsystemConstants.Lift.DOWN_SPEED_SUPPLIER));
	}

}
