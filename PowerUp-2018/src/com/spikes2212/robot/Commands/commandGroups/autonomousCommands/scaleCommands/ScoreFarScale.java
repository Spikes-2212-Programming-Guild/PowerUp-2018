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

public class ScoreFarScale extends CommandGroup {

	public static final Supplier<Double> SLOW_DRIVING_SPEED = ConstantHandler
			.addConstantDouble("score far scale  - slow driving speed", 0.4);
	public static final Supplier<Double> SLOW_DRIVING_TIME_OUT = ConstantHandler
			.addConstantDouble("score far scale  - slow driving timeout", 1);

	/**
	 * This commandGroup scores a power cube to the far scale. This should be
	 * run only if the start side is the same side as the scale.
	 * 
	 * @param startSide
	 */
	public ScoreFarScale(char startSide) {

		// driving to the scale set point
		addSequential(new MoveToSetpointWithEncoders(MoveToSetpointWithEncoders.BETWEEN_SWITCH_AND_SCALE));

		// rotate 90 degrees
		addSequential(new TurnWithEncoders(startSide == 'L' ? -90 : 90));

		// drive to the correct x position of the scale
		addSequential(new MoveToSetpointWithEncoders(MoveToSetpointWithEncoders.FAR_SCALE_SET_POINT));

		// rotate -90 degrees
		addSequential(new TurnWithEncoders(startSide == 'L' ? 90 : -90));

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
		addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.LOCK_SPEED));
	}
}
