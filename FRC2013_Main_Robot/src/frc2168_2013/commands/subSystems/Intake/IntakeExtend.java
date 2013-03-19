package frc2168_2013.commands.subSystems.Intake;

import frc2168_2013.commands.CommandBase;

/**
 * A command to engage the hanger mechanism.
 * 
 * @author ICW
 *
 */
public class IntakeExtend extends CommandBase {

	public IntakeExtend() {
		requires (hanger);
	}

	
	protected void initialize() {
		//Nothing to do
	}

	
	protected void execute() {
		hanger.engage();
	}

	
	protected void interrupted() {
		//Nothing to do
	}

	
	protected void end() {
		//Nothing to do
	}

	
	protected boolean isFinished() {
		//Keep engaging the hanger until another command come along that
		// requires the hanger.
		return false;
	}
}
