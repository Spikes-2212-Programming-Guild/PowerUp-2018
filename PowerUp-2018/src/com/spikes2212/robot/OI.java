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
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI /* GEVALD */ {
	private Joystick driverLeft = new Joystick(0);
	private Joystick driverRight = new Joystick(1);
	
	private XboXUID navigatorXbox = new XboXUID(2);
	
	
	//navigator
	private Button liftSwitchXbox;
	private Button liftLowScaleXbox;
	private Button liftMidScaleXbox;
	private Button placeCubeXbox;
	private Button prepareToPickCubeXbox;
	private Button pickUpCubeXbox;
	
	public OI() {
		initNavigator();
	}
	
	private void initNavigator() {
		liftSwitchXbox = navigatorXbox.getDownButton();
		liftLowScaleXbox = navigatorXbox.getRightButton();	
		liftMidScaleXbox = navigatorXbox.getUpButton();
		placeCubeXbox = navigatorXbox.getYellowButton();
		prepareToPickCubeXbox = navigatorXbox.getGreenButton();
		pickUpCubeXbox = navigatorXbox.getRedButton();
		
		liftSwitchXbox.toggleWhenPressed(new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.SWITCH));
		liftLowScaleXbox.toggleWhenPressed(new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.LOW_SCALE));
		liftMidScaleXbox.toggleWhenPressed(new MoveLiftToTarget(SubsystemComponents.Lift.HallEffects.MID_SCALE));
		placeCubeXbox.toggleWhenPressed(new PlaceCube());
		prepareToPickCubeXbox.toggleWhenPressed(new PrepareToPickUp());
		pickUpCubeXbox.toggleWhenPressed(new PickUpCube());
	}

	public double getLiftUp() {
		return navigatorXbox.getRTAxis();
	}
	
	public double getLiftDown() {
		return navigatorXbox.getLTAxis();
	}
	
	public double getForward() {
		return driverRight.getY();
	}

	public double getRotation() {
		return driverLeft.getX();
	}
	
}
