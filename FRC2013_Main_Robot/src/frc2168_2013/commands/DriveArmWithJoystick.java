package frc2168_2013.commands;

import frc2168_2013.OI;


public class DriveArmWithJoystick extends CommandBase {
	
	public DriveArmWithJoystick(){
		requires(arm);
	}

	protected void end() {
		// TODO Auto-generated method stub
	}

	protected void execute() {
		arm.driveArm(oi.operatorDrive.getRawAxis(OI.leftJoyAxis));
	}

	protected void initialize() {
		// TODO Auto-generated method stub
	}

	protected void interrupted() {
		// TODO Auto-generated method stub
	}

	protected boolean isFinished() {
		return false;
	}

}