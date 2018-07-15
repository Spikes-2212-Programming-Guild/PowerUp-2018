package com.spikes2212.robot.simpleCommand;

import com.spikes2212.robot.Commands.commandGroups.PlaceCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Yellow extends CommandGroup {

    public Yellow() {
      addSequential(new Drive(0.32,0.3,3));
      addSequential(new Drive(0.3,0.4,2));
      addSequential(new TurnLeft(90));
      addSequential(new Drive(0.3,0.4,2));
      addSequential(new Drive(0.32,0.3,3));
      addSequential(new PlaceCube());
    }
}
