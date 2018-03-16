package com.spikes2212.robot.Commands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTankWithPID;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;
import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnWithIMU extends Command {

	public static final Supplier<Double> TURNING_KP = ConstantHandler
			.addConstantDouble("auto turn with imu - turning kp", 1);

	public static final Supplier<Double> TURNING_KI = ConstantHandler
			.addConstantDouble("auto turn with imu - turning ki", 0.7);
	public static final Supplier<Double> TURNING_KD = ConstantHandler
			.addConstantDouble("auto turn with imu - turning kd", 0.1);
	public static final Supplier<Double> PID_WAIT_TIME = ConstantHandler
			.addConstantDouble("auto turn with imu - PID wait time", 0.05);

	public static final Supplier<Double> TOLERANCE = ConstantHandler.addConstantDouble("auto turn with imu - tolerance",
			4);

	private double degrees;

	private Command turn;

	public TurnWithIMU(double degrees) {
		requires(Robot.drivetrain);
	}

	@Override
	protected void initialize() {

		double currentAngle = SubsystemComponents.Drivetrain.IMU.getYaw();
		double leftSetPoint = currentAngle + degrees;
		double rightSetPoint = -currentAngle - degrees;

		PIDSettings settings = new PIDSettings(TURNING_KP.get(), TURNING_KI.get(), TURNING_KD.get(), TOLERANCE.get(),
				PID_WAIT_TIME.get());

		turn = new DriveTankWithPID(Robot.drivetrain, leftSource, rightSource, leftSetPoint, rightSetPoint, settings);

		turn.start();
	}

	@Override
	protected boolean isFinished() {
		return !turn.isRunning();
	}

	@Override
	protected void end() {
		Robot.drivetrain.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

	private static PIDSource leftSource = new PIDSource() {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
		}

		@Override
		public double pidGet() {
			return SubsystemComponents.Drivetrain.IMU.getYaw();
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}
	};

	private static PIDSource rightSource = new PIDSource() {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
		}

		@Override
		public double pidGet() {
			return -SubsystemComponents.Drivetrain.IMU.getYaw();
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}
	};
}
