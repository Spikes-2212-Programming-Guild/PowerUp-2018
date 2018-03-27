package com.spikes2212.robot.Commands.commandGroups.autonomousCommands.temp;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.Commands.TurnWithIMU;
import com.spikes2212.robot.Commands.commandGroups.autonomousCommands.MoveToSetpointWithEncoders;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FarScaleAuto extends CommandGroup {

	public static final Supplier<Double> FAR_SCALE_SET_POINT = ConstantHandler.addConstantDouble("far scale set point",
			250);

	public FarScaleAuto(char startSide) {
		// drive to scale setPoint/
		addSequential(new MoveToSetpointWithEncoders(MoveToSetpointWithEncoders.BETWEEN_SWITCH_AND_SCALE));

		// turn 90 degrees
		addSequential(new TurnWithIMU(Robot.drivetrain,
				() -> startSide == 'L' ? -TurnWithIMU.ROTATE_SPEED.get() : TurnWithIMU.ROTATE_SPEED.get(), 90,
				TurnWithIMU.ROTATE_TOLERANCE.get()));

		// drive to far scale
		addSequential(new MoveToSetpointWithEncoders(FAR_SCALE_SET_POINT));

		// turn -90 degrees
		addSequential(new TurnWithIMU(Robot.drivetrain,
				() -> startSide == 'L' ? -TurnWithIMU.ROTATE_SPEED.get() : TurnWithIMU.ROTATE_SPEED.get(), -90,
				TurnWithIMU.ROTATE_TOLERANCE.get()));
	}
}
