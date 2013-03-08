package frc2168_2013.commands.subSystems.Arm;

import frc2168_2013.OI;
import frc2168_2013.commands.CommandBase;


public class DriveArmWithJoystick extends CommandBase {
	
	public DriveArmWithJoystick(){
		requires(arm);
	}

	protected void execute() {
		arm.setArmPWM(oi.getoperatorDriveRightStick()); //Oh so fast!
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
	
	protected void end() {
		// TODO Auto-generated method stub
	}

}
