package frc2168_2013.commands.subSystems.Shooter;

import frc2168_2013.OI;
import frc2168_2013.commands.CommandBase;


public class DriveShooterWithJoystick extends CommandBase {
	
	public DriveShooterWithJoystick(){
		requires(shooterWheel);
	}

	protected void end() {
		// TODO Auto-generated method stub
	}

	protected void execute() {
		shooterWheel.tankDrive(oi.getoperatorDriveLeftStick(),oi.getoperatorDriveRightStick());
		//shooter.setShooterPWM(oi.getTestAxis(3));		//For testing w/o PID
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
