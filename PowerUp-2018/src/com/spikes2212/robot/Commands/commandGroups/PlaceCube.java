package com.spikes2212.robot.Commands.commandGroups;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystemToTarget;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.robot.SubsystemConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceCube extends CommandGroup {

	public PlaceCube() {
		// TODO check if we need to also roll the cube out
		addSequential(new MoveBasicSubsystem(Robot.folder, SubsystemConstants.Folder.DOWN_SPEED));
		addSequential(new MoveBasicSubsystem(Robot.claw, SubsystemConstants.Claw.OPEN_SPEED));
		/*addSequential(new MoveBasicSubsystemToTarget(Robot.roller, SubsystemConstants.Roller.ROLL_OUT_SPEED,
				() -> SubsystemComponents.Roller.LIGHT_SENSOR.get()));*/
		addSequential(new MoveBasicSubsystem(Robot.folder, SubsystemConstants.Folder.UP_SPEED));

	}
}