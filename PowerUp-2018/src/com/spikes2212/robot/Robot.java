/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.spikes2212.robot;

import com.spikes2212.dashboard.DashBoardController;
import com.spikes2212.genericsubsystems.BasicSubsystem;
import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystemWithTimeSinceReachingLimit;
import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.genericsubsystems.utils.InvertedConsumer;
import com.spikes2212.genericsubsystems.utils.limitationFunctions.TwoLimits;
import com.spikes2212.robot.Commands.commandGroups.MoveLift;
import com.spikes2212.robot.Commands.commandGroups.MoveLiftToTarget;
import com.spikes2212.robot.Commands.commandGroups.PickUpCube;
import com.spikes2212.robot.Commands.commandGroups.PlaceCube;
import com.spikes2212.robot.Commands.commandGroups.StopEverything;
import com.spikes2212.robot.Commands.commandGroups.autonomousCommands.MiddleToSwitchAuto;
import com.spikes2212.utils.CamerasHandler;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static OI oi;
	public static BasicSubsystem climber;
	public static BasicSubsystem folder;
	public static BasicSubsystem roller;
	public static BasicSubsystem liftLocker;
	public static BasicSubsystem lift;
	public static TankDrivetrain drivetrain;

	public static DashBoardController dbc;
	public static CamerasHandler camerasHandler;
	public static String gameData;
	public static SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		roller = new BasicSubsystem((Double speed) -> {
			SubsystemComponents.Roller.MOTOR_RIGHT.set(speed);
			SubsystemComponents.Roller.MOTOR_LEFT.set(-speed);
		}, new TwoLimits(() -> false, () -> SubsystemComponents.Roller.hasCube()));

		// TODO - check which side is really inverted
		drivetrain = new TankDrivetrain(SubsystemComponents.Drivetrain.LEFT_MOTOR::set,
				new InvertedConsumer(SubsystemComponents.Drivetrain.RIGHT_MOTOR::set));

		climber = new BasicSubsystem(SubsystemComponents.Climber.MOTOR::set,
				(Double speed) -> SubsystemConstants.Climber.MAX_VOLTAGE.get() >= SubsystemComponents.Climber.MOTOR
						.getOutputCurrent());

		folder = new BasicSubsystem(new InvertedConsumer(SubsystemComponents.Folder.MOTORS::set),
				new TwoLimits(SubsystemComponents.Folder.MAX_LIMIT::get, () -> false));

		liftLocker = new BasicSubsystem(SubsystemComponents.LiftLocker.MOTOR::set, new TwoLimits(
				SubsystemComponents.LiftLocker.LIMIT_UNLOCKED::get, SubsystemComponents.LiftLocker.LIMIT_LOCKED::get));

		lift = new BasicSubsystem(SubsystemComponents.Lift.MOTORS::set, (Double speed) -> {
			if (speed == 0) // The lift can always move with 0.
				return true;
			// Returns false if the lift tries to move up when its in its upper
			// limit.
			if (SubsystemComponents.Lift.LIMIT_UP.get() && speed > SubsystemConstants.Lift.STAYING_SPEED.get())
				return false;
			// Returns false if the lift tries to move down when its in its
			// lower limit.
			if (SubsystemComponents.Lift.LIMIT_DOWN.get() && speed < SubsystemConstants.Lift.STAYING_SPEED.get())
				return false;
			return true;
		});

		oi = new OI();

		drivetrain.setDefaultCommand(new DriveArcade(drivetrain, oi::getForward, oi::getRotation));

		lift.setDefaultCommand(new MoveBasicSubsystem(lift, () -> SubsystemComponents.LiftLocker.LIMIT_LOCKED.get()
				? 0.0 : SubsystemConstants.Lift.STAYING_SPEED.get()));

		liftLocker.setDefaultCommand(new MoveBasicSubsystem(liftLocker, SubsystemConstants.LiftLocker.LOCK_SPEED));

		camerasHandler = new CamerasHandler(640, 360, RobotMap.USB.FRONT_CAMERA, RobotMap.USB.REAR_CAMERA);
		camerasHandler.setExposure(47);

		dbc = new DashBoardController();

		chooser.addDefault("center", new MiddleToSwitchAuto());
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		new MoveBasicSubsystem(liftLocker, SubsystemConstants.LiftLocker.LOCK_SPEED).start();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		dbc.update();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		chooser.getSelected().start();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SubsystemComponents.Lift.updateLiftPosition();
		dbc.update();
	}

	@Override
	public void teleopInit() {
		chooser.getSelected().cancel();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SubsystemComponents.Lift.updateLiftPosition();
		dbc.update();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
