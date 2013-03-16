package frc2168_2013.commands.subSystems.Arm;

import frc2168_2013.RobotMap;
import frc2168_2013.commands.CommandBase;


public class DriveArmWithConstant extends CommandBase {
	
	public DriveArmWithConstant(){
		requires(arm);
		setTimeout(1);
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
