package frc2168_2013.commands.subSystems.Intake;

import frc2168_2013.commands.CommandBase;

/**
 * A command to actuate the intake to stow position.
 * 
 * @author Shriji
 *
 */
public class IntakeLoadPosition extends CommandBase {

	public IntakeLoadPosition() {
		requires (intake);
	}

	
	protected void initialize() {
		//Nothing to do here
	}

	/**
	 * Sets the intake to the stow position
	 */
	protected void execute() {
		intake.Stow();
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
