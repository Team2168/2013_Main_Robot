package frc2168_2013.commands.subSystems.Arm;

import frc2168_2013.commands.CommandBase;

public class ResetEncoder extends CommandBase {
	

	
	protected void end() {
		// TODO Auto-generated method stub

	}

	
	protected void execute() {
		// TODO Auto-generated method stub

	}

	
	protected void initialize() {
		// TODO Auto-generated method stub
		arm.armPosController.resetEncoder();
	}

	
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
