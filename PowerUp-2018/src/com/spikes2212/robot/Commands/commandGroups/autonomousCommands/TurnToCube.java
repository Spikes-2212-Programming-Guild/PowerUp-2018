package com.spikes2212.robot.Commands.commandGroups.autonomousCommands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.robot.ImageProcessingConstants;
import com.spikes2212.utils.RunnableCommand;

/**
 * rotate until cube is in the given tolerance from the middle of the camera
 */
public class TurnToCube extends DriveArcade {

	// the powerCube pipeline name
	public static final String POWER_CUBE_PIPELINE_NAME = "power-cube";
	// rotation tolerance
	public static final Supplier<Double> TOLERANCE = ConstantHandler.addConstantDouble("turn to cube - tolerance", 0.2);

	public TurnToCube(TankDrivetrain drivetrain, Supplier<Double> moveValueSupplier,
			Supplier<Double> rotateValueSupplier) {
		super(drivetrain, moveValueSupplier, rotateValueSupplier);

	}

	@Override
	protected void initialize() {
		// switch to the power cube pipeline
		new RunnableCommand(() -> ImageProcessingConstants.NETWORK_TABLE.getEntry("pipelineName")
				.setString(POWER_CUBE_PIPELINE_NAME)).start();
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(ImageProcessingConstants.CENTER.pidGet()) <= TOLERANCE.get();
	}
}
