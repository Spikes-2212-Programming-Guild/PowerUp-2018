package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcadeWithPID;
import com.spikes2212.robot.ImageProcessingConstants;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.robot.SubsystemConstants;
import com.spikes2212.robot.Commands.TurnToReflector;
import com.spikes2212.robot.Commands.commandGroups.MoveLift;
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
public class MoveToSwitchFromMiddle extends CommandGroup {

	public static final Supplier<Double> FORWARDS_SPEED = ConstantHandler
			.addConstantDouble("ScoreSwitchFromTheMiddle - forwards speed", 0.2);
	public static final Supplier<Double> ROTATION_SPEED = ConstantHandler
			.addConstantDouble("ScoreSwitchFromTheMiddle - rotation speed", 0.4);

	public static final Supplier<Double> ORIENTATION_FORWARDS_SPEED = ConstantHandler
			.addConstantDouble("ScoreSwitchFromTheMiddle - orientation forwards speed", 0.2);

	public static final Supplier<Double> ORIENTATION_KP = ConstantHandler
			.addConstantDouble("ScoreSwitchFromTheMiddle - oriantation kp", 0.7);
	public static final Supplier<Double> ORIENTATION_KI = ConstantHandler
			.addConstantDouble("ScoreSwitchFromTheMiddle - oriantation ki", 0.01);
	public static final Supplier<Double> ORIENTATION_KD = ConstantHandler
			.addConstantDouble("ScoreSwitchFromTheMiddle - oriantation kd", 0.1);
	public static final Supplier<Double> WAIT_TIME = ConstantHandler
			.addConstantDouble("ScoreSwitchFromTheMiddle - wait time", 0.5);

	public MoveToSwitchFromMiddle() {
		/*
		 * Might want to first move just forwards without turning and only
		 * afterwards turn.
		 */
		addSequential(new TurnToReflector(Robot.drivetrain, FORWARDS_SPEED,
				() -> Robot.gameData.charAt(0) == 'L' ? ROTATION_SPEED.get() : ROTATION_SPEED.get() * -1));
		addSequential(new DriveArcadeWithPID(Robot.drivetrain, ImageProcessingConstants.CENTER, () -> 0.0,
				ORIENTATION_FORWARDS_SPEED,
				new PIDSettings(ORIENTATION_KP.get(), ORIENTATION_KI.get(), ORIENTATION_KD.get(), 0, 1), 2), WAIT_TIME.get());
		addSequential(new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.SWITCH));
		addSequential(new PlaceCube());
		addSequential(new MoveLift(SubsystemConstants.Lift.DOWN_SPEED));
	}
}
