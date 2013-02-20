package frc2168_2013.commands;

import frc2168_2013.OI;


public class DriveHopper_TestingPurposesOnly extends CommandBase {
	
	public DriveHopper_TestingPurposesOnly(){
		requires(hopper);
	}

	protected void end() {
		// TODO Auto-generated method stub
	}

	protected void execute() {
		//hopper.drivehopper(oi.getbaseDriverRightAxis());
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
