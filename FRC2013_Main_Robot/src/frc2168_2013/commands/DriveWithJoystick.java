package frc2168_2013.commands;

import frc2168_2013.RobotMap;


public class DriveWithJoystick extends CommandBase {
	
	public DriveWithJoystick(){
		requires(driveTrain);
	}

	protected void end() {
		// TODO Auto-generated method stub
	}

	protected void execute() {
		driveTrain.setPWM(oi.baseDriver.getRawAxis(RobotMap.rightJoyAxis), oi.baseDriver.getRawAxis(RobotMap.leftJoyAxis));
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
