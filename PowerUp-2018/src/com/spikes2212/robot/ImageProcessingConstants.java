package com.spikes2212.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class ImageProcessingConstants {
	public static final NetworkTable NT = NetworkTableInstance.create().getTable("ImageProcessing");
}
