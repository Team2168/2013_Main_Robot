package frc2168_2013.commands.subSystems.Hopper;

import frc2168_2013.OI;
import frc2168_2013.commands.CommandBase;


public class DriveHopperJoystick extends CommandBase {
	
	double speed;
	
	public DriveHopperJoystick(double speed){
		requires(hopper);
		
		this.speed= speed;
	}

	protected void end() {
		// TODO Auto-generated method stub
	}

	protected void execute() {
		hopper.driveHopperPWM(speed);
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
