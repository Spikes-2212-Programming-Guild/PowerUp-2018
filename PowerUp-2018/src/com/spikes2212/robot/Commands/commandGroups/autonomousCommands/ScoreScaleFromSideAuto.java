package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTankWithPID;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.robot.SubsystemConstants;
import com.spikes2212.robot.Commands.commandGroups.MoveLift;
import com.spikes2212.robot.Commands.commandGroups.PlaceCube;
import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreScaleFromSideAuto extends CommandGroup {

	public static final Supplier<Double> DRIVING_SET_POINT = ConstantHandler
			.addConstantDouble("score scale from side auto - switch set point", 168);

	// defining PID constants
	public static final Supplier<Double> DRIVING_TOLERANCE = ConstantHandler
			.addConstantDouble("score scale from side auto - tolerance", 168);
	public static final Supplier<Double> PID_WAIT_TIME = ConstantHandler
			.addConstantDouble("score scale from side auto - pid wait time", 168);

	public static final Supplier<Double> DRIVING_KP = ConstantHandler
			.addConstantDouble("score scale from side auto - driving kp", 0.7);
	public static final Supplier<Double> DRIVING_KI = ConstantHandler
			.addConstantDouble("score scale from side auto - driving ki", 0.01);
	public static final Supplier<Double> DRIVING_KD = ConstantHandler
			.addConstantDouble("score scale from side auto - driving kd", 0.1);

	// defining turning constants
	public static final Supplier<Double> TURNING_SPEED = ConstantHandler
			.addConstantDouble("score switch from side auto - turning speed", 168);
	public static final Supplier<Double> TURNING_TIME_OUT = ConstantHandler
			.addConstantDouble("score switch from side auto - turning timeout", 168);

	public ScoreScaleFromSideAuto(String gameData, char startSide) {
		// moving to the switch
		addSequential(new MoveToSwitch());

		// turning to the scale and driving towards it
		addSequential(new DriveArcade(Robot.drivetrain, () -> 0.0,
				() -> (startSide == 'L') ? TURNING_SPEED.get() : -TURNING_SPEED.get()));

		PIDSettings settings = new PIDSettings(DRIVING_KP.get(), DRIVING_KI.get(), DRIVING_KD.get(),
				DRIVING_TOLERANCE.get(), PID_WAIT_TIME.get());
		addSequential(new DriveTankWithPID(Robot.drivetrain, SubsystemComponents.Drivetrain.LEFT_ENCODER,
				SubsystemComponents.Drivetrain.RIGHT_ENCODER, DRIVING_SET_POINT, settings));

		// lifting the lift and placing the cube
		addSequential(new MoveLift(SubsystemConstants.Lift.FIRST_UP_SPEED));
		addSequential(new PlaceCube());
	}
}
