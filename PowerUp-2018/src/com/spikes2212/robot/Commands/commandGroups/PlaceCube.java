package com.spikes2212.robot.Commands.commandGroups;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
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

	public static final Supplier<Double> ROLL_OUT_TIME = ConstantHandler.addConstantDouble("Roll Out Time", 1.0);
	public static final Supplier<Double> FOLDER_DOWN_TIME = ConstantHandler.addConstantDouble("Folder Down Time", 1.5);

	public PlaceCube(Supplier<Double> rollOutSpeed) {
		// addSequntial(new MoveBasicSubsystemToTarget(Robot.folder,
		// SubsystemConstants.Folder.FIRST_DOWN_SPEED,
		// SubsystemComponents.Folder.MIN_LIMIT::get));
		// addParallel(new MoveBasicSubsystem(Robot.folder,
		// SubsystemConstants.Folder.STAYING_SPEED),
		// FOLDER_DOWN_TIME.get());
		// addSequential(new MoveBasicSubsystem(Robot.roller, rollOutSpeed),
		// ROLL_OUT_TIME.get());

		addParallel(new MoveBasicSubsystem(Robot.folder, SubsystemConstants.Folder.DOWN_SPEED_SUPPLIER));
		addParallel(new MoveBasicSubsystem(Robot.roller, rollOutSpeed), ROLL_OUT_TIME.get());
	}
}
