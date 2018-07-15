package com.spikes2212.robot.simpleCommand;

import com.spikes2212.robot.Commands.commandGroups.PlaceCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Yellow extends CommandGroup {

    public Yellow() {
      addSequential(new Drive(0.6,0.5,2));
      addSequential(new TurnLeft(45));
      addSequential(new Drive(0.6,0.5,1));
      addSequential(new Drive(0.4,0.5,1));
      addSequential(new TurnLeft(45));
      addSequential(new Drive(0.6,0.5,2));
      
    }
}
