package frc2168_2013.commands.subSystems.Intake;

import frc2168_2013.commands.CommandBase;

/**
 * A command to actuate the intake to the load position
 * 
 * @author Shriji
 *
 */
public class IntakeLoadPosition extends CommandBase {

	public IntakeLoadPosition() {
		requires(intake);
	}

	
	protected void initialize() {
		//Nothing to do
	}

	
	protected void execute() {
		intake.HopperToLoad();
	}

	
	protected void interrupted() {
		//Nothing to do
	}

	
	protected void end() {
		//Nothing to do
	}

	
	protected boolean isFinished() {
		return false;
	}
}