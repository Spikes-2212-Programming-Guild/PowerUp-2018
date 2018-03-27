package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MiddleToSwitchAuto2 extends CommandGroup{
	public MiddleToSwitchAuto2(String gameData) {
		// middle to switch
		addSequential(new MiddleToSwitchAuto(gameData));
		
		// drive back
		
		// rotate with imu
		
		// drive forward and pickup cube
		
		// drive back
		
		// rotate with imu
		
		// drive to switch and put cube
	}
}
