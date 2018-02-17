/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.spikes2212.robot;

import com.spikes2212.robot.Commands.commandGroups.MoveLiftToTarget;
import com.spikes2212.robot.Commands.commandGroups.PickUpCube;
import com.spikes2212.robot.Commands.commandGroups.PlaceCube;
import com.spikes2212.robot.Commands.commandGroups.PrepareToPickUp;
import com.spikes2212.utils.XboXUID;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI /* GEVALD */ {
	private Joystick driverLeft = new Joystick(0);
	private Joystick driverRight = new Joystick(1);
	
	private XboXUID navigator = new XboXUID(2);
	
	//navigator
	private Button liftSwitch;
	private Button liftLowScale;
	private Button liftMidScale;
	private Button placeCube;
	private Button prepareToPickCube;
	private Button pickUpCube;
	
	public OI() {
		initNavigator();
	}
	
	private void initNavigator() {
		liftSwitch = navigator.getDownButton();
		liftLowScale = navigator.getLeftButton();	
		liftMidScale = navigator.getUpButton();
		placeCube = navigator.getYellowButton();
		prepareToPickCube = navigator.getGreenButton();
		pickUpCube = navigator.getRedButton();
		
		liftSwitch.toggleWhenPressed(new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.SWITCH));
		liftLowScale.toggleWhenPressed(new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.LOW_SCALE));
		liftMidScale.toggleWhenPressed(new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.MID_SCALE));
		placeCube.toggleWhenPressed(new PlaceCube());
		prepareToPickCube.toggleWhenPressed(new PrepareToPickUp());
		pickUpCube.toggleWhenPressed(new PickUpCube());
	}
	
	public double getForward() {
		return -driverRight.getY();
	}

	public double getRotation() {
		return driverLeft.getX();
	}
	
}
