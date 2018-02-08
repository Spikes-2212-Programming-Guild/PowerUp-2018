/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.spikes2212.robot;

import com.spikes2212.utils.XboXUID;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI /* GEVALD */ {
	private Joystick driverLeft = new Joystick(0);
	private Joystick driverRight = new Joystick(1);
	
	private XboXUID navigator = new XboXUID(2);
	
	
	//navigator
	private JoystickButton liftSwitchButton;
	private JoystickButton liftLowScaleButton;
	private JoystickButton liftMidScaleButton;
	private JoystickButton placeCubeButton;
	private JoystickButton prepareToPickCubeButton;
	private JoystickButton pickUpCubeButton;
	
	public OI() {
		
	}

	public double getLiftUp() {
		return navigator.getRTAxis();
	}
	
	public double getLiftDown() {
		return navigator.getLTAxis();
	}
	
	public double getForward() {
		return driverRight.getY();
	}

	public double getRotation() {
		return driverLeft.getX();
	}
	
}
