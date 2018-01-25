package com.spikes2212.robot;

import com.spikes2212.genericsubsystems.commands.MoveBasicSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Protocol54657374x0 {
	
	
	
	public static void dashboardInit() {
		initClimber();
		initFolder();
		initClaw();
		initLift();
		initRoller();
	}
	
	public static void initClimber() {
		SmartDashboard.putData("OpenClaw", new MoveBasicSubsystem(Robot.claw, SubsystemConstants.Claw.OPEN_SPEED));
		SmartDashboard.putData("CloseClaw", new MoveBasicSubsystem(Robot.claw, SubsystemConstants.Claw.CLOSE_SPEED));
	}
	
	public static void initFolder() {
		SmartDashboard.putData("MoveFolder up", new MoveBasicSubsystem(Robot.folder, SubsystemConstants.Folder.UP_SPEED));
		SmartDashboard.putData("MoveLift Down",new MoveBasicSubsystem(Robot.folder, SubsystemConstants.Folder.DOWN_SPEED));
	}
	
	public static void
}
