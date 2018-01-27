package com.spikes2212.robot;

import javax.swing.text.StyleContext.SmallAttributeSet;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;
import com.spikes2212.robot.Commands.commandGroups.MoveLiftToTarget;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Protocol54657374x0 {
	
	
	
	public static void dashboardInit() {
		initClimber();
		initFolder();
		initClaw();
		initLift();
		initRoller();
		initSuppliers();
	}
	
	public static void initClaw() {
		SmartDashboard.putData("Open Claw", new MoveBasicSubsystem(Robot.claw, SubsystemConstants.Claw.OPEN_SPEED));
		SmartDashboard.putData("Close Claw", new MoveBasicSubsystem(Robot.claw, SubsystemConstants.Claw.CLOSE_SPEED));
	}
	
	public static void initFolder() {
		SmartDashboard.putData("Move Folder up", new MoveBasicSubsystem(Robot.folder, SubsystemConstants.Folder.UP_SPEED));
		SmartDashboard.putData("Move Folder Down",new MoveBasicSubsystem(Robot.folder, SubsystemConstants.Folder.DOWN_SPEED));
	}
	
	public static void initClimber() {
		SmartDashboard.putData("Climb",new MoveBasicSubsystem(Robot.climber, SubsystemConstants.Climber.SPEED));
	}
	
	public static void initRoller() {
		SmartDashboard.putData("Take Cube",new MoveBasicSubsystem(Robot.roller, SubsystemConstants.Roller.TAKE_SPEED));
		SmartDashboard.putData("Throw Cube",new MoveBasicSubsystem(Robot.roller, SubsystemConstants.Roller.THROW_SPEED) );
	}
	
	public static void initLift() {
		SmartDashboard.putData("Move Lift Up",new MoveLiftToTarget(true));
		SmartDashboard.putData("Move Lift Down", new MoveLiftToTarget(false));
	}
	
	public static void initSuppliers(){
		Robot.dbc.addBoolean("Lift Up", SubsystemComponents.Lift.LIMIT_UP::get);
		Robot.dbc.addBoolean("Lift Down", SubsystemComponents.Lift.LIMIT_DOWN::get);
		Robot.dbc.addBoolean("LiftLocker Up", SubsystemComponents.Claw.LIMIT::get);
		Robot.dbc.addBoolean("LiftLocker Down", SubsystemComponents.LiftLocker.LIMIT_DOWN::get);
		Robot.dbc.addBoolean("Hall Mid Scale", SubsystemComponents.Lift.HALL_EFFECTS_MID_SCALE::get);
		Robot.dbc.addBoolean("Hall Low Scale", SubsystemComponents.Lift.HALL_EFFECTS_LOW_SCALE::get);
		Robot.dbc.addBoolean("Hall Switch", SubsystemComponents.Lift.HALL_EFFECTS_SWITCH::get);
		Robot.dbc.addDouble("Lift Position",() -> SubsystemComponents.Lift.position);
		Robot.dbc.addDouble("ClawCurrent", () -> SubsystemComponents.Claw.MOTOR.getOutputCurrent());
	}
}

