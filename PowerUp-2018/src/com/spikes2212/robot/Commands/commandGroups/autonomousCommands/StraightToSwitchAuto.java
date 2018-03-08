package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcadeWithPID;
import com.spikes2212.robot.ImageProcessingConstants;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.robot.SubsystemConstants;
import com.spikes2212.robot.Commands.commandGroups.MoveLiftToTarget;
import com.spikes2212.robot.Commands.commandGroups.PlaceCube;
import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StraightToSwitchAuto extends CommandGroup {
	// orienting constants
	public static final Supplier<Double> TOLERANCE = ConstantHandler
			.addConstantDouble("score switch straight auto - orienting tolerance", 0);
	public static final Supplier<Double> PID_WAIT_TIME = ConstantHandler
			.addConstantDouble("score switch straight  auto PID waitTime", 1);
	public static final Supplier<Double> ORIENTATION_FORWARD_SPEED = ConstantHandler
			.addConstantDouble("score switch straight - orientation forward speed", 0.5);
	public static final Supplier<Double> ORIENTATION_TIME_OUT = ConstantHandler
			.addConstantDouble("score switch straight - oriantation timeout", 2.3);

	public StraightToSwitchAuto() {
		// move lift
		addSequential(new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.SWITCH));
		addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.LOCK_SPEED));

		// drive while orienting
		addSequential(new DriveArcadeWithPID(Robot.drivetrain, ImageProcessingConstants.CENTER, () -> 0.0,
				ORIENTATION_FORWARD_SPEED,
				new PIDSettings(SubsystemConstants.Drivetrain.ORIENTATION_KP.get(),
						SubsystemConstants.Drivetrain.ORIENTATION_KI.get(),
						SubsystemConstants.Drivetrain.ORIENTATION_KD.get(), TOLERANCE.get(), PID_WAIT_TIME.get()),
				ImageProcessingConstants.RANGE), ORIENTATION_TIME_OUT.get());
		// place cube
		addSequential(new PlaceCube());
	}
}
