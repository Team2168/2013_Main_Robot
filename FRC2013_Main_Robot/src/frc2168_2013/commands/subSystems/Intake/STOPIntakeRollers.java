package frc2168_2013.commands.subSystems.Intake;

import frc2168_2013.commands.CommandBase;




public class STOPIntakeRollers extends CommandBase {
	
	private double rightRollerSpeed;
	private double leftRollerSpeed;
	
	/*
	 * Method allows you to drive intake with constant speed
	 */
	public STOPIntakeRollers(){
		requires(intake);
	}

	
	protected void end() {
		// TODO Auto-generated method stub
	}

	
	protected void execute() {
		intake.driveIntake(0.0, 0.0);
		
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
