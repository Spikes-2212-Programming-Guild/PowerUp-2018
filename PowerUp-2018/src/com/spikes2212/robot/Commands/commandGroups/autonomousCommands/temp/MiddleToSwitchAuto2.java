package com.spikes2212.robot.Commands.commandGroups.autonomousCommands.temp;

import java.util.function.Supplier;

import org.opencv.core.RotatedRect;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.robot.SubsystemConstants;
import com.spikes2212.robot.Commands.TurnWithIMU;
import com.spikes2212.robot.Commands.commandGroups.MoveLift;
import com.spikes2212.robot.Commands.commandGroups.MoveLiftToTarget;
import com.spikes2212.robot.Commands.commandGroups.PickUpCube;
import com.spikes2212.robot.Commands.commandGroups.PlaceCube;
import com.spikes2212.robot.Commands.commandGroups.autonomousCommands.MiddleToSwitchAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MiddleToSwitchAuto2 extends CommandGroup {
	// backwards drive data
	public static final Supplier<Double> BACKWARDS_SPEED = ConstantHandler.addConstantDouble("middle 2 bacwards speed",
			-0.5);
	public static final Supplier<Double> BACKWARDS_TIME = ConstantHandler.addConstantDouble("middle 2 bacwards time",
			0.4);
	// rotation data
	public static final Supplier<Double> ANGLE = ConstantHandler.addConstantDouble("middle 2 rotation angle", 90);
	// forward drive to cube data
	public static final Supplier<Double> FORWARD_SPEED_TO_CUBE = ConstantHandler
			.addConstantDouble("middle 2 forward speed to cube", 0.4);
	public static final Supplier<Double> FORWARD_TIME_TO_CUBE = ConstantHandler
			.addConstantDouble("middle 2 forward time to cube", 1.5);

	// forward drive to switch
	public static final Supplier<Double> FORWARD_SPEED_TO_SWITCH = ConstantHandler
			.addConstantDouble("middle 2 forward speed to switch", 0.6);
	public static final Supplier<Double> FORWARD_TIME_TO_SWITCH = ConstantHandler
			.addConstantDouble("middle 2 forward time to switch", 0.6);

	public MiddleToSwitchAuto2(String gameData) {

		// middle to switch
		addSequential(new MiddleToSwitchAuto(gameData));

//		// drive back
//		addSequential(new DriveArcade(Robot.drivetrain, BACKWARDS_SPEED, () -> 0.0), BACKWARDS_TIME.get());
//
//		// rotate with imu and move lift down
//		addParallel(new MoveLift(SubsystemConstants.Lift.DOWN_SPEED_SUPPLIER));
//		addParallel(new PickUpCube(), 4 );
//		addSequential(new TurnWithIMU(Robot.drivetrain,
//				() -> gameData.charAt(0) == 'L' ? -TurnWithIMU.ROTATE_SPEED.get() : TurnWithIMU.ROTATE_SPEED.get(),
//				ANGLE.get(), TurnWithIMU.ROTATE_TOLERANCE.get()));
//
//		// drive forward and pickup cube
//		addSequential(new DriveArcade(Robot.drivetrain, FORWARD_SPEED_TO_CUBE, () -> 0.0), FORWARD_TIME_TO_CUBE.get());
//
//		// drive back
//		addSequential(new DriveArcade(Robot.drivetrain, () -> -FORWARD_SPEED_TO_CUBE.get(), () -> 0.0),
//				FORWARD_TIME_TO_CUBE.get());
//
//		// rotate with imu
//		addSequential(new TurnWithIMU(Robot.drivetrain,
//				() -> gameData.charAt(0) == 'L' ? TurnWithIMU.ROTATE_SPEED.get() : -TurnWithIMU.ROTATE_SPEED.get(),
//				-ANGLE.get(), TurnWithIMU.ROTATE_TOLERANCE.get()));
//
//		// drive to switch and move lift up
//		addParallel(new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.SWITCH));
//		addSequential(new DriveArcade(Robot.drivetrain, FORWARD_SPEED_TO_SWITCH, () -> 0.0),
//				FORWARD_TIME_TO_SWITCH.get());
//
//		// put cube
//		addSequential(new PlaceCube(SubsystemConstants.Roller.SLOW_ROLL_OUT_SPEED));
//	}
	}
}
