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
		arm.setArmPWM(oi.getoperatorDriveRightStick() * 0.7); //Oh so fast!
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
