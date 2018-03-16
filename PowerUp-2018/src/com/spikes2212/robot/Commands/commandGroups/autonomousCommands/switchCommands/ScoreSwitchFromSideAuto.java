package com.spikes2212.robot.Commands.commandGroups.autonomousCommands.switchCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.robot.SubsystemConstants;
import com.spikes2212.robot.Commands.commandGroups.MoveLiftToTarget;
import com.spikes2212.robot.Commands.commandGroups.PlaceCube;
import com.spikes2212.robot.Commands.commandGroups.autonomousCommands.MoveToSetpointWithEncoders;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreSwitchFromSideAuto extends CommandGroup {

	// defining PID set point of the switch
		public static final Supplier<Double> SWITCH_SET_POINT = ConstantHandler
				.addConstantDouble("move to switch - switch set point", 135);
	
	// defining turning constants
	public static final Supplier<Double> TURNING_SPEED = ConstantHandler
			.addConstantDouble("score switch from side auto - turning speed", 0.5);
	public static final Supplier<Double> TURNING_TIME_OUT = ConstantHandler
			.addConstantDouble("score switch from side auto - turning timeout", 1.2);

	public static final Supplier<Double> FORWARD_SPEED = ConstantHandler
			.addConstantDouble("score switch from side auto - forward speed", 0.4);
	public static final Supplier<Double> FORWARD_TIME_OUT = ConstantHandler
			.addConstantDouble("score switch from side auto - forward timeout", 1.5);

	public ScoreSwitchFromSideAuto(char startSide) {

		addSequential(new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.SWITCH));
		addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.LOCK_SPEED));
		// driving to the correct switch set point
		addSequential(new MoveToSetpointWithEncoders(SWITCH_SET_POINT));

		// turning towards the switch
		addSequential(new DriveArcade(Robot.drivetrain, () -> 0.0,
				() -> startSide == 'L' ? -TURNING_SPEED.get() : TURNING_SPEED.get()), TURNING_TIME_OUT.get());

		// moving to the switch and placing the cube
		addSequential(new DriveArcade(Robot.drivetrain, FORWARD_SPEED, () -> 0.0), FORWARD_TIME_OUT.get());
		addSequential(new PlaceCube());
	}
}