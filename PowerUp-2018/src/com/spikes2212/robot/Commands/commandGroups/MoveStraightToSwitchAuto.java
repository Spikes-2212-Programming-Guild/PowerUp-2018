package com.spikes2212.robot.Commands.commandGroups;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveStraightToSwitchAuto extends CommandGroup {

    public MoveStraightToSwitchAuto() {
    	addParallel(new DriveArcade(Robot.drivetrain, SubsystemConstants.Drivetrain.AUTO_STRAIGHT_SPEED, () -> 0.0));
    	addParallel(new MoveBasicSubsystem(Robot.folder, SubsystemConstants.Folder.DOWN_SPEED));
    }
}
