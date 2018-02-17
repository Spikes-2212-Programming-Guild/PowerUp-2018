package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.robot.SubsystemConstants;
import com.spikes2212.robot.Commands.commandGroups.MoveLift;
import com.spikes2212.robot.Commands.commandGroups.MoveLiftToTarget;
import com.spikes2212.robot.Commands.commandGroups.PlaceCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveStraightAndPutCubeInSwitchAuto extends CommandGroup {

	public static final Supplier<Double> FORWARD_SPEED = ConstantHandler
			.addConstantDouble("ScoreScaleFromSide - Forward Speed", 0.4);
	public static final Supplier<Double> MOVING_WAIT_TIME = ConstantHandler
			.addConstantDouble("DriveStraightAndPutCubeInSwitch - Wait Time", 0.5);

	public DriveStraightAndPutCubeInSwitchAuto(char startSide) {
		// drive to switch by time
		addSequential(new DriveArcade(Robot.drivetrain, FORWARD_SPEED, () -> 0.0), MOVING_WAIT_TIME.get());
		// the starting side is the correct side
		if (Robot.gameData.charAt(0) == startSide) {
			// prepare lift
			addSequential(new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.SWITCH));
			// put cube
			addSequential(new PlaceCube());
			// move lift down
			addSequential(new MoveLift(SubsystemConstants.Lift.DOWN_SPEED));
		}

	}
}
