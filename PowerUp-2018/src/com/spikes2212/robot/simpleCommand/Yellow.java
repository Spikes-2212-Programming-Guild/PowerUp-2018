package com.spikes2212.robot.simpleCommand;

import com.spikes2212.robot.Commands.commandGroups.PlaceCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Yellow extends CommandGroup {

    public Yellow() {
      addSequential(new Drive(0.5,0.5,3));
      addSequential(new Drive(0.5,0.7,2));
      addSequential(new TurnLeft(90));
      addSequential(new Drive(0.5,0.6,2.5));
      addSequential(new Drive(0.5,0.5,3));
      addSequential(new PlaceCube());
    }
}
