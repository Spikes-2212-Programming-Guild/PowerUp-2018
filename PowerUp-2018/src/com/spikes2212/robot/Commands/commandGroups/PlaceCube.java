package com.spikes2212.robot.Commands.commandGroups;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceCube extends CommandGroup {

	public static final Supplier<Double> ROLL_OUT_TIME = ConstantHandler.addConstantDouble("Roll Out Time", 1.0);

	public PlaceCube() {
	//	addParallel(new MoveBasicSubsystem(Robot.folder, SubsystemConstants.Folder.DOWN_SPEED_SUPPLIER));
		addSequential(new MoveBasicSubsystem(Robot.roller, SubsystemConstants.Roller.ROLL_OUT_SPEED),
				ROLL_OUT_TIME.get());

	}
}
