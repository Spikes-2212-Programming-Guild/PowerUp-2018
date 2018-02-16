package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcadeWithPID;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTankWithPID;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.robot.SubsystemConstants;
import com.spikes2212.robot.Commands.commandGroups.MoveLift;
import com.spikes2212.robot.Commands.commandGroups.MoveLiftToTarget;
import com.spikes2212.robot.Commands.commandGroups.PlaceCube;
import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveStraightTurnAndPutCubeInObjectiveAuto extends CommandGroup {

	public static final Supplier<Double> FORWARD_SPEED = ConstantHandler
			.addConstantDouble("ScoreScaleFromSide - Forward Speed", 0.4);
	public static final Supplier<Double> ROTATE_SPEED = ConstantHandler
			.addConstantDouble("ScoreScaleFromSide - Rotate Speed", 0.4);
	public static final Supplier<Double> KP = ConstantHandler.addConstantDouble("ScoreScaleFromSide - kp", 0.7);
	public static final Supplier<Double> KI = ConstantHandler.addConstantDouble("ScoreScaleFromSide - ki", 0.04);
	public static final Supplier<Double> KD = ConstantHandler.addConstantDouble("ScoreScaleFromSide - kd", 0.1);
	public static final Supplier<Double> TOLERANCE = ConstantHandler.addConstantDouble("ScoreScaleFromSide - Tolerance",
			0.5);
	public static final Supplier<Double> MOVING_WAIT_TIME = ConstantHandler
			.addConstantDouble("ScoreScaleFromSide - Wait Time", 0.5);
	public static final Supplier<Double> DISTANCE_FROM_SCALE = ConstantHandler
			.addConstantDouble("ScoreScaleFromSide - Distance From Scale", 20);

	public static final Supplier<Double> ROTATE_TIME_OUT = ConstantHandler
			.addConstantDouble("ScoreScaleFromSide - time out", 1);

	public static final Supplier<Double> DISTANCE_FROM_SWITCH = ConstantHandler
			.addConstantDouble("ScoreScaleFromSide - Distance From Scale", 0.4);

	public DriveStraightTurnAndPutCubeInObjectiveAuto(AutoObjective objective, char startSide) {
		if(Robot.gameData.charAt(0) == startSide) {
			addSequential(new DriveTankWithPID(Robot.drivetrain, SubsystemComponents.Drivetrain.LEFT_ENCODER, SubsystemComponents.Drivetrain.RIGHT_ENCODER,
					objective.setPoint, 
					new PIDSettings(KP.get(), KI.get(), KD.get(), TOLERANCE.get(), MOVING_WAIT_TIME.get())));
			addSequential(
					new DriveArcade(Robot.drivetrain, () -> 0.0,
						() -> Robot.gameData.charAt(0) == 'L' ? ROTATE_SPEED.get() : ROTATE_SPEED.get() * -1),
					ROTATE_TIME_OUT.get());
			addSequential(objective.raiseLiftCommand);
			addSequential(new PlaceCube());
			addSequential(new MoveLift(SubsystemConstants.Lift.DOWN_SPEED));
		}else
			addSequential(new DriveTankWithPID(Robot.drivetrain, SubsystemComponents.Drivetrain.LEFT_ENCODER, SubsystemComponents.Drivetrain.RIGHT_ENCODER,
					objective.setPoint, 
					new PIDSettings(KP.get(), KI.get(), KD.get(), TOLERANCE.get(), MOVING_WAIT_TIME.get())));
	}

	public static enum AutoObjective {
		SWITCH(DISTANCE_FROM_SWITCH, new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.SWITCH)), SCALE(
				DISTANCE_FROM_SCALE, new MoveLift(SubsystemConstants.Lift.UP_SPEED));

		private Supplier<Double> setPoint;
		private Command raiseLiftCommand;

		AutoObjective(Supplier<Double> setPoint, Command raiseLiftCommand) {
			this.setPoint = setPoint;
			this.raiseLiftCommand = raiseLiftCommand;
		}

		public Supplier<Double> getSetPoint() {
			return setPoint;
		}

		public Command getRaiseLiftCommand() {
			return raiseLiftCommand;
		}
	}
}
