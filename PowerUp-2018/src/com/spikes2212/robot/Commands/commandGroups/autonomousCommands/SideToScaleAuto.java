package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.robot.SubsystemConstants;
import com.spikes2212.robot.Commands.commandGroups.MoveLift;
import com.spikes2212.robot.Commands.commandGroups.PlaceCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SideToScaleAuto extends CommandGroup {

	public static final Supplier<Double> FORWARD_SPEED = ConstantHandler.addConstantDouble("scale auto - forward speed",
			0.4);
	// TODO check rotation side

	// orienting constants
	public static final Supplier<Double> TOLERANCE = ConstantHandler
			.addConstantDouble("scale auto - orienting tolerance", 0);
	public static final Supplier<Double> PID_WAIT_TIME = ConstantHandler.addConstantDouble("scale auto PID waitTime",
			0.5);
	
	// set point constants
	// FIXME change setpoints to real values
	public static final Supplier<Double> SET_POINT_SCALE = ConstantHandler
			.addConstantDouble("move to scale - scale set point", 135);
	public static final Supplier<Double> SET_POINT_CLOSE_PLATE = ConstantHandler
			.addConstantDouble("move to scale - scale set point", 135);
	public static final Supplier<Double> SET_POINT_FAR_PLATE = ConstantHandler
			.addConstantDouble("move to scale - scale set point", 135);
	public static final Supplier<Double> SET_POINT_GET_CLOSE_TO_SCALE = ConstantHandler
			.addConstantDouble("Move to scale - getting close to scale", 0.07);

	public SideToScaleAuto(char startSide, String gameData) {
		//drive straight 
		addSequential(new MoveToObjectiveWithEncoders(SET_POINT_SCALE));
		// turn 90 degrees 
		addSequential(
				new DriveArcade(Robot.drivetrain, () -> 0.0,
						() -> (startSide == 'L' ? -SubsystemConstants.Drivetrain.ROTATION_SPEED.get()
								: SubsystemConstants.Drivetrain.ROTATION_SPEED.get())),
				SubsystemConstants.Drivetrain.ROTATION_TIME.get());
		// drive to near or far scale
		addSequential(new MoveToObjectiveWithEncoders(
				(startSide == gameData.charAt(1)) ? SET_POINT_CLOSE_PLATE : SET_POINT_FAR_PLATE));
		// turn to scale
		addSequential(
				new DriveArcade(Robot.drivetrain, () -> 0.0,
						() -> (startSide == 'L' ? SubsystemConstants.Drivetrain.ROTATION_SPEED.get()
								: -SubsystemConstants.Drivetrain.ROTATION_SPEED.get())),
				SubsystemConstants.Drivetrain.ROTATION_TIME.get());
		// raise lift
		addSequential(new MoveLift(SubsystemConstants.Lift.UP_SPEED));
		// get close to scale
		addSequential(new MoveToObjectiveWithEncoders(SET_POINT_GET_CLOSE_TO_SCALE));
		// release the cube
		addSequential(new PlaceCube());
		// go back from scale
		addSequential(new MoveToObjectiveWithEncoders(() -> -SET_POINT_GET_CLOSE_TO_SCALE.get()));
		// lower lift
		addSequential(new MoveLift(SubsystemConstants.Lift.DOWN_SPEED));
	}

}
