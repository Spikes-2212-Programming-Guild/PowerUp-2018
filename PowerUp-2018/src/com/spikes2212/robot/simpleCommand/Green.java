package com.spikes2212.robot.simpleCommand;

import com.spikes2212.robot.Commands.commandGroups.PlaceCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Green extends CommandGroup {

    public Green() {
    	addSequential(new Drive(1,1,1));
    	addSequential(new TurnLeft(45));
    	addSequential(new Drive(1,1,0.5));
    	addSequential(new TurnRight(90));
    	addSequential(new Drive(1,1,0.5));
    	addSequential(new TurnLeft(45));
    	addSequential(new Drive(1,1,3));
    	addSequential(new TurnRight(90));
    	addSequential(new Drive(1,1,1.5));
    	addSequential(new TurnLeft(90));
    	addSequential(new PlaceCube());
    	addSequential(new TurnLeft(90));
    	addSequential(new Drive(1,1,0.25));
    	addSequential(new TurnRight(90));
    	addSequential(new Drive (1,1,1.25));
    	addSequential(new TurnLeft(90));
    	addSequential(new Drive(1,1,2));
    }
}
