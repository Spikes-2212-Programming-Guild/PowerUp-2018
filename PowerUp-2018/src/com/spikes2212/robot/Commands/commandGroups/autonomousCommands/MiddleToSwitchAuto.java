package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.robot.SubsystemConstants;
import com.spikes2212.robot.Commands.commandGroups.MoveLiftToTarget;
import com.spikes2212.robot.Commands.commandGroups.PlaceCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * This command group moves the robot from the middle to your side of the
 * switch. First it moves forwards and turns to your side of the switch until it
 * sees a light sensor and move to it.
 */
public class MiddleToSwitchAuto extends CommandGroup {

	public MiddleToSwitchAuto(String gameData) {
		addParallel(new DriveToSwitchFromMiddle(gameData));
		// move lift
		addSequential(new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.SWITCH));
		addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.LOCK_SPEED));
	}
	@Override
	protected void end() {
		super.end();
		new PlaceCube().start();
	}
}
