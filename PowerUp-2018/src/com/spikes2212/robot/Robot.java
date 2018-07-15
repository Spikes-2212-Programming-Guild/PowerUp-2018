/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.spikes2212.robot;

import javax.management.ImmutableDescriptor;

import com.spikes2212.dashboard.DashBoardController;
import com.spikes2212.genericsubsystems.basicSubsystem.BasicSubsystem;
import com.spikes2212.genericsubsystems.basicSubsystem.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.basicSubsystem.utils.limitationFunctions.TwoLimits;
import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.robot.Commands.commandGroups.MoveLift;
import com.spikes2212.robot.Commands.commandGroups.MoveLiftToTarget;
import com.spikes2212.robot.Commands.commandGroups.PickUpCube;
import com.spikes2212.robot.Commands.commandGroups.PlaceCube;
import com.spikes2212.robot.Commands.commandGroups.StopEverything;
import com.spikes2212.robot.Commands.commandGroups.autonomousCommands.MiddleToSwitchAuto;
import com.spikes2212.robot.Commands.commandGroups.autonomousCommands.PassAutoLine;
import com.spikes2212.robot.Commands.commandGroups.autonomousCommands.ScoreCloseScaleByTime;
import com.spikes2212.robot.Commands.commandGroups.autonomousCommands.ScoreSwitchFromSideAuto;
import com.spikes2212.robot.Commands.commandGroups.autonomousCommands.StraightToSwitchAuto;
import com.spikes2212.robot.simpleCommand.MoveLiftDown;
import com.spikes2212.robot.simpleCommand.MoveLiftUp;
import com.spikes2212.robot.simpleCommand.TurnLeft;
import com.spikes2212.robot.simpleCommand.TurnRight;
import com.spikes2212.robot.simpleCommand.TurnWithIMU;
import com.spikes2212.robot.simpleCommand.Yellow;
import com.spikes2212.utils.CamerasHandler;
import com.spikes2212.utils.InvertedConsumer;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

	public static double ENCODERS_DISTANCE_PER_PULSE = Math.PI * 6 / 360;

	// defining subsystems

	public static BasicSubsystem folder;
	public static BasicSubsystem roller;

	public static BasicSubsystem liftLocker;
	public static BasicSubsystem lift;

	public static TankDrivetrain drivetrain;

	// defining general variables
	public static OI oi;
	public static DashBoardController dbc;
	public static CamerasHandler camerasHandler;

	// defining autonomous variables
	public static String gameData = "";

	public static SendableChooser<String> autoChooser = new SendableChooser<>();
	public static SendableChooser<Character> startSideChooser = new SendableChooser<>();

	public static Command autoCommand;

	@Override
	public void robotInit() {
		roller = new BasicSubsystem((Double speed) -> {
			SubsystemComponents.Roller.MOTOR_RIGHT.set(speed);
			SubsystemComponents.Roller.MOTOR_LEFT.set(-speed);
		}, new TwoLimits(() -> false, () -> SubsystemComponents.Roller.hasCube()));

		// TODO - check which side is really inverted
		drivetrain = new TankDrivetrain(SubsystemComponents.Drivetrain.LEFT_MOTOR::set,
				new InvertedConsumer(SubsystemComponents.Drivetrain.RIGHT_MOTOR::set));

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

		camerasHandler = new CamerasHandler(320, 240, RobotMap.USB.FRONT_CAMERA, RobotMap.USB.REAR_CAMERA);
		camerasHandler.setExposure(80);

		dbc = new DashBoardController();

		SubsystemComponents.Drivetrain.LEFT_ENCODER.setDistancePerPulse(ENCODERS_DISTANCE_PER_PULSE);
		SubsystemComponents.Drivetrain.RIGHT_ENCODER.setDistancePerPulse(ENCODERS_DISTANCE_PER_PULSE);

		SubsystemComponents.Drivetrain.LEFT_ENCODER.setPIDSourceType(PIDSourceType.kDisplacement);
		SubsystemComponents.Drivetrain.RIGHT_ENCODER.setPIDSourceType(PIDSourceType.kDisplacement);

		SubsystemComponents.Drivetrain.LEFT_ENCODER.reset();
		SubsystemComponents.Drivetrain.RIGHT_ENCODER.reset();

		autoChooser.addDefault("pass auto line", "pass auto line");
		autoChooser.addObject("switch from middle", "switch from middle");
		autoChooser.addObject("switch from side", "switch from side");
		autoChooser.addObject("straight to switch", "straight to switch");
		autoChooser.addObject("score close scale by time", "score close scale by time");

		startSideChooser.addDefault("none", 'N');
		startSideChooser.addObject("right", 'R');
		startSideChooser.addObject("left", 'L');

		initDBC();
		initDashboard();
	}

	public static void initDBC() {
		// simple command data

		dbc.addDouble("imu yaw angle", SubsystemComponents.Drivetrain.IMU::getAngleZ);

		dbc.addDouble("Simple commands - turn right angle", SubsystemConstants.Drivetrain.SIMPLE_TURN_RIGHT_ANGLE);
		dbc.addDouble("Simple commands - turn left angle", SubsystemConstants.Drivetrain.SIMPLE_TURN_LEFT_ANGLE);
		// locker data
		dbc.addBoolean("locker - is locked", SubsystemComponents.LiftLocker.LIMIT_LOCKED::get);
		dbc.addBoolean("locker - is unlocked", SubsystemComponents.LiftLocker.LIMIT_UNLOCKED::get);

		// lift data
		dbc.addBoolean("Lift - up", SubsystemComponents.Lift.LIMIT_UP::get);
		dbc.addBoolean("Lift - mid scale", () -> !SubsystemComponents.Lift.HallEffects.MID_SCALE.getHallEffect().get());
		dbc.addBoolean("Lift - low scale", () -> !SubsystemComponents.Lift.HallEffects.LOW_SCALE.getHallEffect().get());
		dbc.addBoolean("Lift - switch", () -> !SubsystemComponents.Lift.HallEffects.SWITCH.getHallEffect().get());
		dbc.addBoolean("Lift - down", SubsystemComponents.Lift.LIMIT_DOWN::get);
		dbc.addDouble("lift - current speed", Robot.lift::getSpeed);
		dbc.addDouble("lift - current position", SubsystemComponents.Lift::getPosition);

		// folder data
		dbc.addBoolean("Folder - Up", SubsystemComponents.Folder.MAX_LIMIT::get);
		dbc.addBoolean("Folder - down", SubsystemComponents.Folder.MIN_LIMIT::get);

		// roller data
		dbc.addBoolean("roller - has cube", SubsystemComponents.Roller::hasCube);

		// general information - robot
		dbc.addDouble("laser distance", () -> (SubsystemConstants.Roller.LASER_SENSOR_CONSTANT.get()
				/ SubsystemComponents.Roller.LASER_SENSOR.getVoltage()));

		dbc.addDouble("IMU - degrees", SubsystemComponents.Drivetrain.IMU::getAngleY);

		dbc.addDouble("encoder left distance",
				() -> ((double) SubsystemComponents.Drivetrain.LEFT_ENCODER.getDistance()));
		dbc.addDouble("encoder right distance",
				() -> ((double) SubsystemComponents.Drivetrain.RIGHT_ENCODER.getDistance()));

		// game state
		dbc.addBoolean("close switch left",
				() -> (gameData != null && gameData.length() > 0) ? (gameData.charAt(0) == 'L') : false);
		dbc.addBoolean("close switch right",
				() -> (gameData != null && gameData.length() > 0) ? (gameData.charAt(0) == 'R') : false);
		dbc.addBoolean("scale left",
				() -> (gameData != null && gameData.length() > 0) ? (gameData.charAt(1) == 'L') : false);
		dbc.addBoolean("scale right",
				() -> (gameData != null && gameData.length() > 0) ? (gameData.charAt(1) == 'R') : false);
		dbc.addBoolean("far switch left",
				() -> (gameData != null && gameData.length() > 0) ? (gameData.charAt(2) == 'L') : false);
		dbc.addBoolean("far switch right",
				() -> (gameData != null && gameData.length() > 0) ? (gameData.charAt(2) == 'R') : false);
	}

	public static void initDashboard() {
		// auto
		SmartDashboard.putData("auto chooser", autoChooser);
		SmartDashboard.putData("start side chooser", startSideChooser);

		// simple commands
		SmartDashboard.putData("Turn left", new TurnLeft(SubsystemConstants.Drivetrain.SIMPLE_TURN_LEFT_ANGLE.get()));
		SmartDashboard.putData("Turn right",
				new TurnRight(SubsystemConstants.Drivetrain.SIMPLE_TURN_RIGHT_ANGLE.get()));
		SmartDashboard.putData("Move lift up", new MoveLiftUp());
		SmartDashboard.putData("Move lift down", new MoveLiftDown());
		SmartDashboard.putData("Place cube", new PlaceCube());

		// locker commands
		SmartDashboard.putData("unlock",
				new MoveBasicSubsystem(liftLocker, SubsystemConstants.LiftLocker.UNLOCK_SPEED));
		SmartDashboard.putData("lock", new MoveBasicSubsystem(liftLocker, SubsystemConstants.LiftLocker.LOCK_SPEED));

		// lift commands
		SmartDashboard.putData("move lift up", new MoveLift(SubsystemConstants.Lift.UP_SPEED));
		SmartDashboard.putData("move lift down", new MoveLift(SubsystemConstants.Lift.DOWN_SPEED_SUPPLIER));
		SmartDashboard.putData("move lift to switch",
				new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.SWITCH));
		SmartDashboard.putData("move lift to low scale",
				new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.LOW_SCALE));
		SmartDashboard.putData("move lift to mid scale",
				new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.MID_SCALE));
		// folder commands
		SmartDashboard.putData("move folder up", new MoveBasicSubsystem(folder, SubsystemConstants.Folder.UP_SPEED));
		SmartDashboard.putData("move folder down",
				new MoveBasicSubsystem(folder, SubsystemConstants.Folder.DOWN_SPEED_SUPPLIER));
		// roller commands
		SmartDashboard.putData("roll in", new MoveBasicSubsystem(roller, SubsystemConstants.Roller.ROLL_IN_SPEED));
		SmartDashboard.putData("roll out", new MoveBasicSubsystem(roller, SubsystemConstants.Roller.ROLL_OUT_SPEED));

		// command groups
		SmartDashboard.putData("pickup cube", new PickUpCube());
		SmartDashboard.putData("place cube", new PlaceCube());
		SmartDashboard.putData("stop everything", new StopEverything());
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		new MoveBasicSubsystem(liftLocker, SubsystemConstants.LiftLocker.LOCK_SPEED).start();
		gameData = "";
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		dbc.update();

		// try to receive data if exists
		if (DriverStation.getInstance().getGameSpecificMessage() != null
				&& DriverStation.getInstance().getGameSpecificMessage().length() > 0)
			gameData = DriverStation.getInstance().getGameSpecificMessage();

		// got the data
		if (gameData.length() > 0) {
			char side = startSideChooser.getSelected();

			switch (autoChooser.getSelected()) {
			case "switch from middle":
				autoCommand = new MiddleToSwitchAuto(gameData);
				break;
			case "switch from side":
				if (side == gameData.charAt(0)) {
					autoCommand = new ScoreSwitchFromSideAuto(side);
					break;
				}
			case "straight to switch":
				if (side == gameData.charAt(0)) {
					autoCommand = new StraightToSwitchAuto();
					break;
				}
			case "score close scale by time":
				if (side == gameData.charAt(1)) {
					autoCommand = new ScoreCloseScaleByTime(side);
					break;
				}
			default:
				autoCommand = new Yellow();
				break;
			}
		}
	}

	@Override
	public void autonomousInit() {
		SubsystemComponents.Drivetrain.LEFT_ENCODER.reset();
		SubsystemComponents.Drivetrain.RIGHT_ENCODER.reset();
		System.out.println("auto chooser command - " + autoChooser.getSelected() + " , starting side - "
				+ startSideChooser.getSelected() + " , game data:" + gameData);
		if (autoCommand != null)
			autoCommand.start();
		else {
			System.out.println("no game data recieved. running pass line");
			new PassAutoLine().start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SubsystemComponents.Lift.updateLiftPosition();
		dbc.update();
	}

	@Override
	public void teleopInit() {
		SubsystemComponents.Drivetrain.LEFT_ENCODER.reset();
		SubsystemComponents.Drivetrain.RIGHT_ENCODER.reset();
		if (autoCommand != null)
			autoCommand.cancel();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SubsystemComponents.Lift.updateLiftPosition();
		dbc.update();
	}

	@Override
	public void testPeriodic() {
		System.out.println("auto command - " + autoChooser.getSelected() + " , starting side - "
				+ startSideChooser.getSelected() + " , game data:" + gameData);
	}
}
