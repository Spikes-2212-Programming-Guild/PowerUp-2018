package com.spikes2212.robot.Commands;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.SubsystemComponents;

/**
 *
 */
public class MoveLift extends MoveBasicSubsystem {

	public MoveLift(Supplier<Double> speed) {
		super(Robot.lift, speed);
	}

	@Override
	protected void execute() {
		super.execute();
		if (SubsystemComponents.Lift.LIMIT_UP.get())
			SubsystemComponents.Lift.position = (speedSupplier.get() > 0) ? 4 : 3.5;
		else if (!SubsystemComponents.Lift.HALL_EFFECTS_MID_SCALE.get())
			SubsystemComponents.Lift.position = (speedSupplier.get() > 0) ? 3.5 : 2.5;
		else if (!SubsystemComponents.Lift.HALL_EFFECTS_LOW_SCALE.get())
			SubsystemComponents.Lift.position = (speedSupplier.get() > 0) ? 2.5 : 1.5;
		else if (!SubsystemComponents.Lift.HALL_EFFECTS_SWITCH.get())
			SubsystemComponents.Lift.position = (speedSupplier.get() > 0) ? 1.5 : 0.5;
		else if (SubsystemComponents.Lift.LIMIT_DOWN.get())
			SubsystemComponents.Lift.position = (speedSupplier.get() > 0) ? 0.5 : 0;

	}

}
