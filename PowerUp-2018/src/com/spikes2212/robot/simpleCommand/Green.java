package com.spikes2212.robot.simpleCommand;

import com.spikes2212.robot.Commands.commandGroups.PlaceCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Green extends CommandGroup {

    public Green() {
    	addSequential(new Drive(0.3,0.3, 1.5));
    	addSequential(new TurnLeft(45));
    	addSequential(new Drive (0.3,0.3,0.55));
    	addSequential(new TurnRight (90));
    	addSequential(new Drive (0.3,0.3,0.55));
    	addSequential(new TurnLeft(45));
    	addSequential(new Drive(0.5, 0.5,2));
    	addSequential(new TurnRight(90));
    	addSequential(new Drive(0.3,0.3,1.2));
    	addSequential(new TurnLeft (90));
    	addSequential(new PlaceCube());
    	addSequential(new TurnLeft(90));
    	addSequential(new Drive(0.3,0.3,0.5));
    	addSequential(new TurnRight(90));
    	addSequential(new Drive(0.3,0.3,0.5));
    	addSequential(new TurnRight(90));
    	addSequential(new Drive(0.3,0.3,0.5));
    	addSequential(new TurnLeft(90));
    	addSequential(new Drive(0.3,0.3,1.2));
    	addSequential(new TurnLeft(90));
    	addSequential(new Drive (0.5,0.5,1.5));
    	

    	
    	
    }
}
