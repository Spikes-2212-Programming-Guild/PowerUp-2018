package com.spikes2212.robot.Commands;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.robot.SubsystemComponents;

public class TurnWithIMU extends DriveArcade {

	private double angle;
	private double tolerance;

	private double wantedAngle;

	public TurnWithIMU(TankDrivetrain drivetrain, Supplier<Double> rotateSpeed, double degrees, double tolerance) {
		super(drivetrain, () -> 0.0, rotateSpeed);
		angle = degrees;
		this.tolerance = tolerance;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		double currentAngle = SubsystemComponents.Drivetrain.IMU.getAngleY();

		wantedAngle = currentAngle + angle;

		System.out.println("wanted angle" + wantedAngle);
		System.out.println("init y angle "+SubsystemComponents.Drivetrain.IMU.getAngleY());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		System.out.println("yaw angle" + SubsystemComponents.Drivetrain.IMU.getAngleY());
		
			
		
		if(Math.abs(wantedAngle - SubsystemComponents.Drivetrain.IMU.getAngleY()) < tolerance){
			System.out.println("end Y angle "+SubsystemComponents.Drivetrain.IMU.getAngleY());	
		}
		return Math.abs(wantedAngle - SubsystemComponents.Drivetrain.IMU.getAngleY()) < tolerance;
	}
}
