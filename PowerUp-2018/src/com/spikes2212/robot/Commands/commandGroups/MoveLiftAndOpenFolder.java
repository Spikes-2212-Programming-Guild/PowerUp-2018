package com.spikes2212.robot.Commands.commandGroups;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemConstants;
import com.spikes2212.robot.Commands.MoveLift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveLiftAndOpenFolder extends CommandGroup {

    public MoveLiftAndOpenFolder(boolean up) {
    		addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.CLOSE_SPEED));
    		addSequential(new MoveLift((up) ? SubsystemConstants.Lift.UP_SPEED : SubsystemConstants.Lift.DOWN_SPEED));
    		addSequential(new MoveBasicSubsystem(Robot.liftLocker, SubsystemConstants.LiftLocker.OPEN_SPEED));
    		addSequential(new MoveBasicSubsystem(Robot.folder, SubsystemConstants.Folder.DOWN_SPEED));
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
