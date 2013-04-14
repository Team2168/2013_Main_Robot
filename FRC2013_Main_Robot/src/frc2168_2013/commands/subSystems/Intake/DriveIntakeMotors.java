package frc2168_2013.commands.subSystems.Intake;

import frc2168_2013.commands.CommandBase;


/**
 * drive intake motors at setspeeds
 * @author Shriji
 *
 */

public class DriveIntakeMotors extends CommandBase {
	
	private double leftRollerSpeed;
	private double rightRollerSpeed;
	
	/*
	 * Method allows you to drive shooter with constant speed
	 */
	public DriveIntakeMotors(double leftRollerSpeed, double rightRollerSpeed){
		requires(intake);
		
		this.leftRollerSpeed = leftRollerSpeed;
		this.rightRollerSpeed = rightRollerSpeed;
	}

	
	protected void end() {
		// TODO Auto-generated method stub
	}

	
	protected void execute() {
		
		intake.DriveIntakeMotors(leftRollerSpeed, leftRollerSpeed);
		
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
