/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.spikes2212.robot;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystemWithTimeSinceReachingLimit;
import com.spikes2212.robot.Commands.commandGroups.MoveLift;
import com.spikes2212.robot.Commands.commandGroups.MoveLiftToTarget;
import com.spikes2212.robot.Commands.commandGroups.PickUpCube;
import com.spikes2212.robot.Commands.commandGroups.PlaceCube;
import com.spikes2212.robot.Commands.commandGroups.StopEverything;
import com.spikes2212.utils.RunnableCommand;
import com.spikes2212.utils.XboXUID;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI /* GEVALD */ {
	private Joystick driverLeft = new Joystick(0);
	private Joystick driverRight = new Joystick(1);

	private XboXUID navigator = new XboXUID(2);

	// driver
	private Button switchToFront;
	private Button switchToRear;
	
	// navigator
	private Button liftSwitch;
	private Button liftLowScale;
	private Button liftMidScale;
	private Button placeCube;
	private Button pickUpCube;
	private Button stop;
	private Button folderUp;
	private Button folderDown;
	private Button liftUp;
	private Button liftDown;

	public OI() {
		initNavigator();
		initDriver();
	}
	private void initDriver(){
		switchToFront = new JoystickButton(driverRight, 1);
		switchToRear = new JoystickButton(driverRight, 2);
		
		switchToFront.whenPressed(new RunnableCommand(()->Robot.camerasHandler.switchCamera(RobotMap.USB.FRONT_CAMERA)));
		switchToRear.whenPressed(new RunnableCommand(()->Robot.camerasHandler.switchCamera(RobotMap.USB.REAR_CAMERA)));
	}

	private void initNavigator() {
		liftSwitch = navigator.getGreenButton();
		liftLowScale = navigator.getRedButton();
		liftMidScale = navigator.getYellowButton();
		placeCube = navigator.getUpButton();
		pickUpCube = navigator.getLeftButton();
		folderUp = navigator.getLBButton();
		folderDown = navigator.getDownButton();
		liftUp = navigator.getRBButton();
		liftDown = navigator.getRightStickButton();
		stop = navigator.getLeftStickButton();

		liftSwitch.toggleWhenPressed(new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.SWITCH));
		liftLowScale.toggleWhenPressed(new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.LOW_SCALE));
		liftMidScale.toggleWhenPressed(new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.MID_SCALE));
		placeCube.toggleWhenPressed(new PlaceCube());
		pickUpCube.toggleWhenPressed(new PickUpCube());
		stop.whenPressed(new StopEverything());
		folderUp.whenPressed(new MoveBasicSubsystem(Robot.folder, SubsystemConstants.Folder.UP_SPEED.get()));
		liftUp.toggleWhenPressed(new MoveLift(SubsystemConstants.Lift.UP_SPEED));
		liftDown.toggleWhenPressed(new MoveLift(
				() -> SubsystemComponents.Lift.getPosition() < SubsystemComponents.Lift.HallEffects.SWITCH.getIndex()
						? SubsystemConstants.Lift.SECOND_DOWN_SPEED.get()
						: SubsystemConstants.Lift.FIRST_DOWN_SPEED.get()));
		folderDown.whenPressed(new MoveBasicSubsystem(Robot.folder, SubsystemConstants.Folder.DOWN_SPEED_SUPPLIER));
	}

	public double getForward() {
		return -driverRight.getY();
	}

	public double getRotation() {
		return -driverLeft.getX();
	}

}
