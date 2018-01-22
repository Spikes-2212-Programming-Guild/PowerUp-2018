/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.spikes2212.robot;

import com.spikes2212.genericsubsystems.BasicSubsystem;
import com.spikes2212.genericsubsystems.limitationFunctions.Limitless;
import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.genericsubsystems.limitationFunctions.TwoLimits;
import com.spikes2212.robot.SubsystemComponents.Drivetrain;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

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
	public static TankDrivetrain drivetrain;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		roller = new BasicSubsystem(SubsystemComponents.Roller.MOTOR::set, new TwoLimits(() -> true, SubsystemComponents.Roller.LIGHT_SENSOR::get));
		drivetrain = new TankDrivetrain(SubsystemComponents.Drivetrain.LEFT_MOTOR::set,
				SubsystemComponents.Drivetrain.RIGHT_MOTOR::set);
		climber = new BasicSubsystem(SubsystemComponents.Climber.MOTOR::set,
				(Double speed) -> SubsystemConstants.Climber.MAX_VOLTAGE.get() >= SubsystemComponents.Climber.MOTOR
						.getOutputCurrent());
		folder = new BasicSubsystem(SubsystemComponents.Folder.MOTOR::set,
				new TwoLimits(SubsystemComponents.Folder.MAX_LIMIT::get, SubsystemComponents.Folder.MIN_LIMIT::get));
		claw = new BasicSubsystem(SubsystemComponents.Claw.MOTOR::set, new TwoLimits(
				() -> SubsystemConstants.Claw.MAX_VOLTAGE.get() >= SubsystemComponents.Claw.MOTOR.getOutputCurrent(),
				SubsystemComponents.Claw.LIMIT::get));
		oi = new OI();
		drivetrain.setDefaultCommand(new DriveArcade(drivetrain, oi::getForward, oi::getRotation));
		// chooser.addObject("My Auto", new MyAutoCommand());
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
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
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		 * switch(autoSelected) { case "My Auto": autonomousCommand = new
		 * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
		 * ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
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
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
