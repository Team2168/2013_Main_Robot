package frc2168_2013.commands.subSystems.ShooterWheel;

import frc2168_2013.commands.CommandBase;




public class DriveShooterWithConstant extends CommandBase {
	
	private double aftWheelSpeed;
	private double fwdWheelSpeed;
	
	public DriveShooterWithConstant(double aftWheelSpeed, double fwdWheelSpeed){
		requires(shooterWheel);
		
		this.aftWheelSpeed = aftWheelSpeed;
		this.fwdWheelSpeed = fwdWheelSpeed;
	}

	
	protected void end() {
		// TODO Auto-generated method stub
	}

	
	protected void execute() {
		shooterWheel.tankDrive(aftWheelSpeed,fwdWheelSpeed);
		
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
