package com.spikes2212.robot.Commands;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.robot.SubsystemConstants;
import com.spikes2212.robot.SubsystemComponents;

public class DriveArcadeWithSpeedLimitation extends DriveArcade {

	public DriveArcadeWithSpeedLimitation(TankDrivetrain drivetrain, Supplier<Double> moveValueSupplier,
			Supplier<Double> rotateValueSupplier) {
		super(drivetrain, limitSpeed(moveValueSupplier), limitSpeed(rotateValueSupplier));
	}

	public DriveArcadeWithSpeedLimitation(TankDrivetrain drivetrain, double moveValue, double rotateValue) {
		super(drivetrain, moveValue, rotateValue);
	}

	public static Supplier<Double> limitSpeed(Supplier<Double> speedSuplier) {
		return () -> {/*
						 * n = 1 (max voltage), m = (max limit - 1) / 4 -> top
						 * speed = position * (max limit - 1) / 4 + 1.
						 */
			double topSpeed = SubsystemComponents.Lift.getPosition()
					* (SubsystemConstants.Drivetrain.MAX_SPEED_LIMIT.get() - 1) / 4 + 1;
			if (speedSuplier.get() > topSpeed)
				return topSpeed;
			return speedSuplier.get();
		};
	}
}
