package frc2168_2013.commands;

import frc2168_2013.OI;
import frc2168_2013.RobotMap;


public class DriveArmWithConstant extends CommandBase {
	
	public DriveArmWithConstant(){
		requires(arm);
		setTimeout(.8);
	}

	protected void end() {
		// TODO Auto-generated method stub
		arm.setArmPWM(0.0);
	}

	protected void execute() {
		if(arm.armPosController.getSensorPos() < 30)
		arm.setArmPWM(RobotMap.armConstVoltage); //Oh so fast!
	}

	protected void initialize() {
		// TODO Auto-generated method stub
	}

	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

}
