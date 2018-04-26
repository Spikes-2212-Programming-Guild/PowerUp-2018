package com.spikes2212.robot.Commands.commandGroups.autonomousCommands.temp;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.robot.SubsystemConstants;
import com.spikes2212.robot.Commands.TurnWithIMU;
import com.spikes2212.robot.Commands.commandGroups.MoveLift;
import com.spikes2212.robot.Commands.commandGroups.MoveLiftToTarget;
import com.spikes2212.robot.Commands.commandGroups.PickUpCube;
import com.spikes2212.robot.Commands.commandGroups.PlaceCube;
import com.spikes2212.robot.Commands.commandGroups.autonomousCommands.MiddleToSwitchAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MiddleToSwitchAuto3 extends CommandGroup {
	// backwards drive data
	public static final Supplier<Double> BACKWARDS_SPEED = ConstantHandler.addConstantDouble("middle 3 bacwards speed",
			-0.7);
	public static final Supplier<Double> BACKWARDS_TIME = ConstantHandler.addConstantDouble("middle 3 bacwards time",
			0.4);
	// rotation data
	public static final Supplier<Double> ANGLE = ConstantHandler.addConstantDouble("middle 3 rotation angle", 90);
	// forward drive to middle line data
	public static final Supplier<Double> FORWARD_SPEED_TO_MIDDLE_LINE = ConstantHandler
			.addConstantDouble("middle 3 forward speed to middle line", 0.5);
	public static final Supplier<Double> FORWARD_TIME_TO_MIDDLE_LINE = ConstantHandler
			.addConstantDouble("middle 3 forward time to middle line", 1.2);

	// forward drive to cube
	public static final Supplier<Double> FORWARD_SPEED_TO_CUBE = ConstantHandler
			.addConstantDouble("middle 3 forward speed to cube", 0.6);
	public static final Supplier<Double> FORWARD_TIME_TO_CUBE = ConstantHandler
			.addConstantDouble("middle 3 forward time to cube", 0.6);

	public MiddleToSwitchAuto3(String gameData) {

		// middle to switch
		addSequential(new MiddleToSwitchAuto(gameData));

		// drive back
		addSequential(new DriveArcade(Robot.drivetrain, BACKWARDS_SPEED, () -> 0.0), BACKWARDS_TIME.get());

		// rotate to middle line and move lift down
		addParallel(new MoveLift(SubsystemConstants.Lift.DOWN_SPEED_SUPPLIER));
		addSequential(new TurnWithIMU(Robot.drivetrain,
				() -> gameData.charAt(0) == 'L' ? -TurnWithIMU.ROTATE_SPEED.get() : TurnWithIMU.ROTATE_SPEED.get(),
				ANGLE.get(), TurnWithIMU.ROTATE_TOLERANCE.get()));

		// drive forward to middle line
		addSequential(new DriveArcade(Robot.drivetrain, FORWARD_SPEED_TO_MIDDLE_LINE, () -> 0.0),
				FORWARD_TIME_TO_MIDDLE_LINE.get());

		// rotate to cube
		addSequential(new TurnWithIMU(Robot.drivetrain,
				() -> gameData.charAt(0) == 'L' ? -TurnWithIMU.ROTATE_SPEED.get() : TurnWithIMU.ROTATE_SPEED.get(),
				-ANGLE.get(), TurnWithIMU.ROTATE_TOLERANCE.get()));

		// drive to cube and pick it up
		addParallel(new PickUpCube(), FORWARD_TIME_TO_CUBE.get());
		addSequential(new DriveArcade(Robot.drivetrain, FORWARD_SPEED_TO_CUBE, () -> 0.0), FORWARD_TIME_TO_CUBE.get());

		// drive backwards
		addSequential(new DriveArcade(Robot.drivetrain, () -> -FORWARD_SPEED_TO_CUBE.get(), () -> 0.0),
				FORWARD_TIME_TO_CUBE.get());
	}
}
