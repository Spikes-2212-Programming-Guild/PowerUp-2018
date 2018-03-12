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
public class TurnWithEncoders extends Command {
	public static final Supplier<Double> TURNING_KP = ConstantHandler
			.addConstantDouble("auto turn with encoders - turning kp", 1);

	public static final Supplier<Double> PID_WAIT_TIME = ConstantHandler
			.addConstantDouble("auto turn with encoders - wait time", 0.05);

	public static final Supplier<Double> TOLERANCE = ConstantHandler
			.addConstantDouble("auto turn with encoders - tolerance", 4);

	public static final Supplier<Double> ROBOT_WIDTH = ConstantHandler
			.addConstantDouble("auto turn with encoders - Robot width", 4);

	private Command turn;

	private double angle;

	public TurnWithEncoders(double degrees) {
		requires(Robot.drivetrain);

		this.angle = degrees;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		SubsystemComponents.Drivetrain.LEFT_ENCODER.reset();
		SubsystemComponents.Drivetrain.RIGHT_ENCODER.reset();

		double radians = 2 * Math.PI / 180 * angle;

		double rightSetPoint = -radians * ROBOT_WIDTH.get() / 2;
		double leftSetPoint = radians * ROBOT_WIDTH.get() / 2;

		PIDSettings settings = new PIDSettings(TURNING_KP.get(), 0, 0, TOLERANCE.get(), PID_WAIT_TIME.get());

		turn = new DriveTankWithPID(Robot.drivetrain, leftSource, rightSource, leftSetPoint, rightSetPoint, settings);

		turn.start();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return !turn.isRunning();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

	private static PIDSource leftSource = new PIDSource() {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
		}

		@Override
		public double pidGet() {
			return SubsystemComponents.Drivetrain.LEFT_ENCODER.get();
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
			return -SubsystemComponents.Drivetrain.RIGHT_ENCODER.get();
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}
	};

}