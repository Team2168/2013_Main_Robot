package frc2168_2013.commands;

import frc2168_2013.OI;
import frc2168_2013.RobotMap;


public class DriveDrivetrainWithJoystick extends CommandBase {
	
	public DriveDrivetrainWithJoystick(){
		requires(driveTrainL);
		requires(driveTrainR);
	}

	protected void end() {
		// TODO Auto-generated method stub
	}

	protected void execute() {
		driveTrainL.tankDriveLeft(oi.baseDriver.getRawAxis(OI.leftJoyAxis));
		driveTrainR.tankDriveRight(oi.baseDriver.getRawAxis(OI.rightJoyAxis));
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
