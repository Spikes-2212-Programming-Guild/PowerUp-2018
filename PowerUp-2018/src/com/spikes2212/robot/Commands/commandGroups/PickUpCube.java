package com.spikes2212.robot.Commands.commandGroups;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystemWithTimeSinceReachingLimit;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * This command group picks up the cube that is infront of it.
 * 
 * @author Matan
 */
public class PickUpCube extends CommandGroup {

	public static final Supplier<Double> ROLL_IN_WAIT_TIME = ConstantHandler.addConstantDouble("roll in time", 0.5);

	public PickUpCube() {
		addParallel(new MoveBasicSubsystem(Robot.folder, SubsystemConstants.Folder.DOWN_SPEED_SUPPLIER));
		// vacuums the cube
		addSequential(new MoveBasicSubsystemWithTimeSinceReachingLimit(Robot.roller,
				SubsystemConstants.Roller.ROLL_IN_SPEED, ROLL_IN_WAIT_TIME.get()));
		// moving the folder up so we can lift the cube safely with out the
		// robot falling
		addSequential(new MoveBasicSubsystem(Robot.folder, SubsystemConstants.Folder.UP_SPEED));
	}
}
