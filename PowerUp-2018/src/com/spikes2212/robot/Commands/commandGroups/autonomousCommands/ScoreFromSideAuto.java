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

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreFromSideAuto extends CommandGroup {

	public static enum AutonomousTarget {
		SWITCH(new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.SWITCH), SWITCH_SET_POINT), SCALE(
				new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.MID_SCALE), SCALE_SET_POINT);

		private Command moveLiftToTarget;
		private Supplier<Double> setPoint;

		AutonomousTarget(Command moveLiftToTarget, Supplier<Double> setPoint) {
			this.moveLiftToTarget = moveLiftToTarget;
			this.setPoint = setPoint;
		}

		public Command getLiftCommand() {
			return moveLiftToTarget;
		}

		public Supplier<Double> getSetPoint() {
			return setPoint;
		}
	}

	public static final Supplier<Double> SWITCH_SET_POINT = ConstantHandler
			.addConstantDouble("score from side auto - switch set point", 168);
	public static final Supplier<Double> SCALE_SET_POINT = ConstantHandler
			.addConstantDouble("score from side auto - scale set point", 168);

	public static final Supplier<Double> TOLERANCE = ConstantHandler
			.addConstantDouble("score from side auto - tolerance", 168);
	public static final Supplier<Double> PID_WAIT_TIME = ConstantHandler
			.addConstantDouble("score from side auto - pid wait time", 168);

	public static final Supplier<Double> TURNING_SPEED = ConstantHandler
			.addConstantDouble("score from side auto - turning speed", 168);
	public static final Supplier<Double> TURNING_TIME_OUT = ConstantHandler
			.addConstantDouble("score from side auto - turning timeout", 168);

	public ScoreFromSideAuto(AutonomousTarget target, String gameData, char startSide) {
		addSequential(target.getLiftCommand());

		PIDSettings settings = new PIDSettings(SubsystemConstants.Drivetrain.DRIVING_KP.get(),
				SubsystemConstants.Drivetrain.DRIVING_KI.get(), SubsystemConstants.Drivetrain.DRIVING_KD.get(),
				TOLERANCE.get(), PID_WAIT_TIME.get());

		addSequential(new DriveTankWithPID(Robot.drivetrain, SubsystemComponents.Drivetrain.LEFT_ENCODER,
				SubsystemComponents.Drivetrain.RIGHT_ENCODER, target.getSetPoint(), settings));

		addSequential(new DriveArcade(Robot.drivetrain, () -> 0.0, TURNING_SPEED), TURNING_TIME_OUT.get());

		addSequential(target.getLiftCommand());

		if (gameData.charAt(0) == startSide)
			addSequential(new PlaceCube());

	}
}
