package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTankWithPID;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.robot.SubsystemConstants;
import com.spikes2212.robot.Commands.commandGroups.MoveLiftToTarget;
import com.spikes2212.robot.Commands.commandGroups.PlaceCube;
import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreSwitchFromSideAuto extends CommandGroup {

	// defining PID set point of the switch
	public static final Supplier<Double> SET_POINT = ConstantHandler
			.addConstantDouble("score switch from side auto - switch set point", 168);
	// public static final Supplier<Double> SCALE_SET_POINT = ConstantHandler
	// .addConstantDouble("score switch from side auto - scale set point", 168);

	// defining PID constants
	public static final Supplier<Double> TOLERANCE = ConstantHandler
			.addConstantDouble("score switch from side auto - tolerance", 168);
	public static final Supplier<Double> PID_WAIT_TIME = ConstantHandler
			.addConstantDouble("score switch from side auto - pid wait time", 168);

	// defining turning constants
	public static final Supplier<Double> TURNING_SPEED = ConstantHandler
			.addConstantDouble("score switch from side auto - turning speed", 168);
	public static final Supplier<Double> TURNING_TIME_OUT = ConstantHandler
			.addConstantDouble("score switch from side auto - turning timeout", 168);

	public ScoreSwitchFromSideAuto(String gameData, char startSide) {

		// driving to the correct set point according to target's properties

		PIDSettings settings = new PIDSettings(SubsystemConstants.Drivetrain.DRIVING_KP.get(),
				SubsystemConstants.Drivetrain.DRIVING_KI.get(), SubsystemConstants.Drivetrain.DRIVING_KD.get(),
				TOLERANCE.get(), PID_WAIT_TIME.get());
		addSequential(new DriveTankWithPID(Robot.drivetrain, SubsystemComponents.Drivetrain.LEFT_ENCODER,
				SubsystemComponents.Drivetrain.RIGHT_ENCODER, SET_POINT, settings));

		// turning towards the target(switch/scale)
		addSequential(new DriveArcade(Robot.drivetrain, () -> 0.0, TURNING_SPEED), TURNING_TIME_OUT.get());

		// moving the lift to the right height and placing the cube
		addSequential(new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.SWITCH));
		addSequential(new PlaceCube());
	}
}