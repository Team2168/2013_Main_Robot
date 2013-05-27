package frc2168_2013.commands.subSystems.Intake;

import frc2168_2013.commands.CommandBase;

/**
 * A command to actuate the intake to the stow position
 * 
 * @author Shriji
 *
 */
public class AccelerometerReading extends CommandBase {

	public AccelerometerReading() {
		requires(intakePos);
	}

	
	protected void initialize() {
		//Nothing to do
	}

	/**
	 * sets intake to stow position
	 */
	protected void execute() {
		intakePos.printAccReadings();
	}

	
	protected void interrupted() {
		//Nothing to do
	}

	
	protected void end() {
		//Nothing to do
	}

	
	protected boolean isFinished() {
		return true;
	}
}
