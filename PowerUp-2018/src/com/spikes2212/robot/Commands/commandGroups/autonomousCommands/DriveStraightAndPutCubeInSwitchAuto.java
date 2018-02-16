package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTankWithPID;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.robot.SubsystemConstants;
import com.spikes2212.robot.Commands.commandGroups.MoveLift;
import com.spikes2212.robot.Commands.commandGroups.MoveLiftToTarget;
import com.spikes2212.robot.Commands.commandGroups.PlaceCube;
import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveStraightAndPutCubeInSwitchAuto extends CommandGroup {

	public static final Supplier<Double> FORWARD_SPEED = ConstantHandler
			.addConstantDouble("ScoreScaleFromSide - Forward Speed", 0.4);
	public static final Supplier<Double> KP = ConstantHandler.addConstantDouble("ScoreScaleFromSide - kp", 0.7);
	public static final Supplier<Double> KI = ConstantHandler.addConstantDouble("ScoreScaleFromSide - ki", 0.04);
	public static final Supplier<Double> KD = ConstantHandler.addConstantDouble("ScoreScaleFromSide - kd", 0.1);
	public static final Supplier<Double> TOLERANCE = ConstantHandler.addConstantDouble("ScoreScaleFromSide - Tolerance",
			0.5);
	public static final Supplier<Double> MOVING_WAIT_TIME = ConstantHandler
			.addConstantDouble("ScoreScaleFromSide - Wait Time", 0.5);
	public static final Supplier<Double> DISTANCE_FROM_SWITCH = ConstantHandler
			.addConstantDouble("ScoreScaleFromSide - Distance From Scale", 0.4);
	
    public DriveStraightAndPutCubeInSwitchAuto() {
    	addSequential(new DriveTankWithPID(Robot.drivetrain, SubsystemComponents.Drivetrain.LEFT_ENCODER, SubsystemComponents.Drivetrain.RIGHT_ENCODER,
				DISTANCE_FROM_SWITCH, 
				new PIDSettings(KP.get(), KI.get(), KD.get(), TOLERANCE.get(), MOVING_WAIT_TIME.get())));
    	addSequential(new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.SWITCH));
		addSequential(new PlaceCube());
		addSequential(new MoveLift(SubsystemConstants.Lift.DOWN_SPEED));
    	
    }
}
