package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcadeWithPID;
import com.spikes2212.robot.ImageProcessingConstants;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.robot.SubsystemConstants;
import com.spikes2212.robot.Commands.commandGroups.MoveLiftToTarget;
import com.spikes2212.robot.Commands.commandGroups.PlaceCube;
import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * This command group moves the robot from the middle to your side of the
 * switch. First it moves forwards and turns to your side of the switch until it
 * sees a light sensor and move to it.
 */
public class MiddleToSwitchAuto extends CommandGroup {

	public static final Supplier<Double> FORWARDS_SPEED = ConstantHandler
			.addConstantDouble("middle to switch auto - forwards speed", 0.5);
	public static final Supplier<Double> ROTATION_SPEED = ConstantHandler
			.addConstantDouble("middle to switch auto - rotation speed", 0.6);
	public static final Supplier<Double> ROTATION_TIME = ConstantHandler
			.addConstantDouble("middle to switch auto  - rotation time", 0.8);

	public static final Supplier<Double> ORIENTATION_FORWARDS_SPEED = ConstantHandler
			.addConstantDouble("middle to switch auto - orientation forwards speed", 0.5);

	public static final Supplier<Double> ORIENTATION_TIME_OUT = ConstantHandler
			.addConstantDouble("middle to switch auto - oriantation timeout", 2.3);

	public MiddleToSwitchAuto() {
		// move lift
		addParallel(new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.SWITCH));

		// turn to the correct direction
		addSequential(
				new DriveArcade(Robot.drivetrain, FORWARDS_SPEED,
						() -> Robot.gameData.charAt(0) == 'L' ? ROTATION_SPEED.get() : -ROTATION_SPEED.get()),
				ROTATION_TIME.get());

		// drive while orienting
		addSequential(new DriveArcadeWithPID(Robot.drivetrain, ImageProcessingConstants.CENTER, () -> 0.0,
				ORIENTATION_FORWARDS_SPEED,
				new PIDSettings(SubsystemConstants.Drivetrain.ORIENTATION_KP.get(),
						SubsystemConstants.Drivetrain.ORIENTATION_KI.get(),
						SubsystemConstants.Drivetrain.ORIENTATION_KD.get(), 0, 1),
				ImageProcessingConstants.RANGE), ORIENTATION_TIME_OUT.get());
		// place cube
		addSequential(new PlaceCube());
	}
}
