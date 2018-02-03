package com.spikes2212.robot;

import java.util.function.Supplier;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class ImageProcessingConstants {
	public static final int CAMERA_WIDTH = 640;
	public static final int CAMERA_HEIGHT = 360;

	public static final NetworkTable NETWORK_TABLE = NetworkTableInstance.getDefault().getTable("ImageProcessing");

	// calculates the center of the big reflective
	public static final Supplier<Double> BIG_OBJECT_CENTER = () -> ((NETWORK_TABLE.getEntry("x0").getDouble(0)
			+ 0.5 * NETWORK_TABLE.getEntry("width0").getDouble(0)) / CAMERA_WIDTH - 0.5);
	// calculates the center of the small reflective
	public static final Supplier<Double> SMALL_OBJECT_CENTER = () -> ((NETWORK_TABLE.getEntry("x1").getDouble(0)
			+ 0.5 * NETWORK_TABLE.getEntry("width1").getDouble(0)) / CAMERA_WIDTH - 0.5);
	// calculates the center of the two reflectives
	public static final Supplier<Double> TWO_OBJECTS_CENTER = () -> (BIG_OBJECT_CENTER.get() + SMALL_OBJECT_CENTER.get()) / 2;

	public static final PIDSource CENTER = new PIDSource() {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
		}

		@Override
		public double pidGet() {
			if (NETWORK_TABLE.getEntry("isUpdated1").getBoolean(false))
				return TWO_OBJECTS_CENTER.get();
			if (NETWORK_TABLE.getEntry("isUpdated0").getBoolean(false))
				return BIG_OBJECT_CENTER.get();
			return 0;
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}
	};
}
