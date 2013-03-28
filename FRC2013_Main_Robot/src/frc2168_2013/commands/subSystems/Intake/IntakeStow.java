package frc2168_2013.commands.subSystems.Intake;

import frc2168_2013.commands.CommandBase;

/**
 * A command to actuate the intake to stow position.
 * 
 * @author ICW
 *
 */
public class IntakeStow extends CommandBase {

	public IntakeStow() {
		requires (intake);
	}

	
	protected void initialize() {
		//Nothing to do here
	}

	
	protected void execute() {
		intake.Stow();
		//sets the intake to stow position
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
