package frc2168_2013.commands;

import frc2168_2013.RobotMap;



public class DriveWithJoystick extends CommandBase {
	
	public DriveWithJoystick(){
		
		requires(driveTrain);
		
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		
		driveTrain.setSpeed(oi.baseDriver.getRawAxis(RobotMap.rightJoyAxis), oi.baseDriver.getRawAxis(RobotMap.leftJoyAxis));

	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isFinished() {
		
		return false;
		
	}

}
