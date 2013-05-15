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
	 * Sets the intake to the stow position
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
		//Nothing to do here
	}

	
	protected void end() {
		//Nothing to do here
	}

	
	protected boolean isFinished() {
		return true;
	}
}
