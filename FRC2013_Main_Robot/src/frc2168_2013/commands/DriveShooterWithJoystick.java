package frc2168_2013.commands;

import frc2168_2013.OI;


public class DriveShooterWithJoystick extends CommandBase {
	
	public DriveShooterWithJoystick(){
		requires(shooter);
	}

	protected void end() {
		// TODO Auto-generated method stub
	}

	protected void execute() {
		shooter.setShooterPWM(oi.getoperatorDriveLeftStick() * .90);
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
