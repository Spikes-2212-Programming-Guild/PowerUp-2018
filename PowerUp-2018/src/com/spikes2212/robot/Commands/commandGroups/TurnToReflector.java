package com.spikes2212.robot.Commands.commandGroups;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.robot.ImageProcessingConstants;

public class TurnToReflector extends DriveArcade {

	public TurnToReflector(TankDrivetrain drivetrain, double moveValue, double rotateValue) {
		super(drivetrain, moveValue, rotateValue);
	}

	public TurnToReflector(TankDrivetrain drivetrain, Supplier<Double> moveValueSupplier,
			Supplier<Double> rotateValueSupplier) {
		super(drivetrain, moveValueSupplier, rotateValueSupplier);
	}

	@Override
	protected boolean isFinished() {
		return ImageProcessingConstants.IS_UPDATED_0.get();
	}
}
