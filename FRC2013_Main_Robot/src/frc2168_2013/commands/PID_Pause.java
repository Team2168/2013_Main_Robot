package frc2168_2013.commands;

import edu.wpi.first.wpilibj.command.Command;

public class PID_Pause extends CommandBase {
	
	public PID_Pause(){
		requires(driveTrain);
	}
	
	protected void initialize() {
		// TODO Auto-generated method stub
		driveTrain.rightPosController.Pause();
		driveTrain.rightSpeedController.Pause();
	}
	
	protected void execute() {
		// TODO Auto-generated method stub

	}
	
	protected void end() {
		// TODO Auto-generated method stub

	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return driveTrain.rightPosController.isEnabled()==false && driveTrain.rightSpeedController.isEnabled() == false;
	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}



}