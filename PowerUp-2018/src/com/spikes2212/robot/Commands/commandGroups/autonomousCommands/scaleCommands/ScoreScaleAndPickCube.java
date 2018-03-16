package com.spikes2212.robot.Commands.commandGroups.autonomousCommands.scaleCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcadeWithPID;
import com.spikes2212.robot.ImageProcessingConstants;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.robot.SubsystemConstants;
import com.spikes2212.robot.Commands.commandGroups.PickUpCube;
import com.spikes2212.robot.Commands.commandGroups.autonomousCommands.TurnToCube;
import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreScaleAndPickCube extends CommandGroup {

	// defining turning constants
	public static final Supplier<Double> TURNING_SPEED = ConstantHandler
			.addConstantDouble("scale and cube - turning speed", 0.4);

	// orientation constants
	public static final Supplier<Double> ORIENTATION_FORWARD_SPEED = ConstantHandler
			.addConstantDouble("auto pick cube - orientation forward speed", 0.45);
	public static final Supplier<Double> TOLERANCE = ConstantHandler
			.addConstantDouble("auto pick cube - orienting tolerance", 0);
	public static final Supplier<Double> PID_WAIT_TIME = ConstantHandler
			.addConstantDouble("auto pick cube - PID waitTime", 1);

	public ScoreScaleAndPickCube(String gameData, char startSide) {

		// score close scale
		if (gameData.charAt(1) == startSide)
			addSequential(new ScoreCloseScale(startSide));
		// score far scale
		else
			addSequential(new ScoreFarScale(startSide));

		// rotate to cube
		addSequential(new TurnToCube(() -> startSide == 'L' ? -TURNING_SPEED.get() : TURNING_SPEED.get()));

		// initiate picking cube sequence
		addParallel(new PickUpCube());

		// chase the cube
		addSequential(new DriveArcadeWithPID(Robot.drivetrain, ImageProcessingConstants.CENTER, () -> 0.0,
				ORIENTATION_FORWARD_SPEED, () -> SubsystemComponents.Roller.hasCube(),
				new PIDSettings(SubsystemConstants.Drivetrain.ORIENTATION_KP.get(),
						SubsystemConstants.Drivetrain.ORIENTATION_KI.get(),
						SubsystemConstants.Drivetrain.ORIENTATION_KD.get(), TOLERANCE.get(), PID_WAIT_TIME.get()),
				ImageProcessingConstants.RANGE));

	}
}
