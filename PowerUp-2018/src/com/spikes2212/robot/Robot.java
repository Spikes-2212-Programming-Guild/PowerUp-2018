/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.spikes2212.robot;

import com.spikes2212.genericsubsystems.BasicSubsystem;
import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.genericsubsystems.utils.limitationFunctions.TwoLimits;
import com.spikes2212.robot.Commands.commandGroups.MoveLift;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
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
	public static BasicSubsystem claw;
	public static BasicSubsystem roller;
	public static BasicSubsystem liftLocker;
	public static BasicSubsystem lift;
	public static TankDrivetrain drivetrain;

	public static String gameData;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		// TODO check which motor is inverted
		roller = new BasicSubsystem((Double speed) -> {
			SubsystemComponents.Roller.MOTOR_RIGHT.set(speed);
			SubsystemComponents.Roller.MOTOR_LEFT.set(-speed);
		}, new TwoLimits(() -> false, () -> !SubsystemComponents.Roller.LIGHT_SENSOR.get()));
		drivetrain = new TankDrivetrain(SubsystemComponents.Drivetrain.LEFT_MOTOR::set,
				SubsystemComponents.Drivetrain.RIGHT_MOTOR::set);
		climber = new BasicSubsystem(SubsystemComponents.Climber.MOTOR::set,
				(Double speed) -> SubsystemConstants.Climber.MAX_VOLTAGE.get() >= SubsystemComponents.Climber.MOTOR
						.getOutputCurrent());
		folder = new BasicSubsystem(SubsystemComponents.Folder.MOTOR::set,
				new TwoLimits(SubsystemComponents.Folder.MAX_LIMIT::get, SubsystemComponents.Folder.MIN_LIMIT::get));
		claw = new BasicSubsystem(SubsystemComponents.Claw.MOTOR::set, new TwoLimits(
				SubsystemComponents.Claw.LIMIT::get,
				() -> SubsystemConstants.Claw.MAX_VOLTAGE.get() <= SubsystemComponents.Claw.MOTOR.getOutputCurrent()));
		liftLocker = new BasicSubsystem(SubsystemComponents.LiftLocker.MOTOR::set, new TwoLimits(
				SubsystemComponents.LiftLocker.LIMIT_UP::get, SubsystemComponents.LiftLocker.LIMIT_DOWN::get));
		lift = new BasicSubsystem(SubsystemComponents.Lift.MOTORS::set,
				new TwoLimits(SubsystemComponents.Lift.LIMIT_UP::get, SubsystemComponents.Lift.LIMIT_DOWN::get));
		oi = new OI();
		drivetrain.setDefaultCommand(new DriveArcade(drivetrain, oi::getForward, oi::getRotation));
		liftLocker.setDefaultCommand(new MoveBasicSubsystem(liftLocker, SubsystemConstants.LiftLocker.LOCK_SPEED));
		
		SmartDashboard.putData("move lift up", new MoveLift(SubsystemConstants.Lift.UP_SPEED));
		SmartDashboard.putData("move lift down", new MoveLift(SubsystemConstants.Lift.DOWN_SPEED));
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
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

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SubsystemComponents.Lift.updateLiftPosition();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SubsystemComponents.Lift.updateLiftPosition();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
