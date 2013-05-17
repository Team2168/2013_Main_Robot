package frc2168_2013.commands.subSystems.Intake;

import frc2168_2013.commands.CommandBase;

/**
 * A command to actuate the intake to stow position.
 * 
 * @author Shriji
 *
 */
public class DriveIntakeTillFull extends CommandBase {

	boolean disc;
	
	public DriveIntakeTillFull() {
		requires (intakeSpeed);
		
	}
	
	protected void initialize() {
		//Nothing to do here
	}	
	

	/**
	 * if discs are present, stop motors and end command. 
	 * if discs aren't present run motors till present.
	 */
	protected void execute() {
		
    	if(intakeSpeed.intakeFull()){
			intakeSpeed.driveIntake(0.0, 0.0);
			disc = true;
		} else{												
			intakeSpeed.driveIntake(0.5, 0.5);				
			disc = false;
		}		
	}

	
	protected void interrupted() {
		intakeSpeed.driveIntake(0.0, 0.0);
		//stop intake motors
	}

	
	protected void end() {
		//Nothing to do here
	}

	
	protected boolean isFinished() {
		return disc = true;
	}
}
