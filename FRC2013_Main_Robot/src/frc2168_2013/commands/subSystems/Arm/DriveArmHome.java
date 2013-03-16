package frc2168_2013.commands.subSystems.Arm;

import frc2168_2013.RobotMap;
import frc2168_2013.commands.CommandBase;


public class DriveArmHome extends CommandBase {
	
	public DriveArmHome(){
		requires(arm);
	}

	
	protected void end() {
		// TODO Auto-generated method stub
		arm.setArmPWM(0.0);
	}

	
	protected void execute() {
			arm.setArmPWM(RobotMap.armConstVoltageHome); //Oh so fast!
	}

	
	protected void initialize() {
		// TODO Auto-generated method stub
	}

	
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

	
	protected boolean isFinished() {
		return arm.lowHardStopPressed();
	}

}
