package com.spikes2212.robot.Commands.commandGroups;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StopEverything extends CommandGroup {

    public StopEverything() {
    	addParallel(new MoveBasicSubsystem(Robot.folder, 0), 0.01);
    	addParallel(new MoveBasicSubsystem(Robot.roller, 0), 0.01);
    	addParallel(new MoveBasicSubsystem(Robot.lift, 0), 0.01);
    }
}
