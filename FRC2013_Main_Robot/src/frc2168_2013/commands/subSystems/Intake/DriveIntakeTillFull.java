package frc2168_2013.commands.subSystems.Intake;

import frc2168_2013.commands.CommandBase;

/**
 * A command to actuate the intake to stow position.
 * 
 * @author Shriji
 *
 */
public class DriveIntakeTillFull extends CommandBase {

	boolean frisbee;
	
	public DriveIntakeTillFull() {
		requires (intake);
		
	}
	
	protected void initialize() {
		//Nothing to do here
	}	
	

	/**
	 * Sets the intake to the stow position
	 */
	protected void execute() {
		
//    	if(intake.getNumberOfDiscs() = 2){
//			intake.driveIntake(0.0, 0.0);
//			frisbee = true;
//		} else{												//if a frisbee isn't present
//			intake.driveIntake(0.5, 0.5);				//catchfrisbeenow
//			frisbee = false;
//		}		
	}

	
	protected void interrupted() {
		//Nothing to do here
	}

	
	protected void end() {
		//Nothing to do here
	}

	
	protected boolean isFinished() {
		return false;
	}
}
