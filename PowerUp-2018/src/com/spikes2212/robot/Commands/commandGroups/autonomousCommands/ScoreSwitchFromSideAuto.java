package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.robot.SubsystemConstants;
import com.spikes2212.robot.Commands.commandGroups.MoveLiftToTarget;
import com.spikes2212.robot.Commands.commandGroups.PlaceCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreSwitchFromSideAuto extends CommandGroup {

	// defining turning constants
	public static final Supplier<Double> TURNING_SPEED = ConstantHandler
			.addConstantDouble("score switch from side auto - turning speed", 0.7);
	public static final Supplier<Double> TURNING_TIME_OUT = ConstantHandler
			.addConstantDouble("score switch from side auto - turning timeout", 1.2);

	public static final Supplier<Double> FORWARD_SPEED = ConstantHandler
			.addConstantDouble("score switch from side auto - forward speed", 0.3);
	public static final Supplier<Double> FORWARD_TIME_OUT = ConstantHandler
			.addConstantDouble("score switch from side auto - forward timeout", 2);

	public ScoreSwitchFromSideAuto(char startSide) {

		// driving to the correct set point according to target's properties
		addSequential(new MoveToSwitch());

		// turning towards the target(switch/scale)
		addSequential(new DriveArcade(Robot.drivetrain, () -> 0.0,
				() -> startSide == 'L' ? -TURNING_SPEED.get() : TURNING_SPEED.get()), TURNING_TIME_OUT.get());

		// moving the lift to the right height and placing the cube
		addSequential(new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.SWITCH));
		addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.LOCK_SPEED));
		addSequential(new DriveArcade(Robot.drivetrain, FORWARD_SPEED, () -> 0.0), FORWARD_TIME_OUT.get());
		addSequential(new PlaceCube());
	}
}