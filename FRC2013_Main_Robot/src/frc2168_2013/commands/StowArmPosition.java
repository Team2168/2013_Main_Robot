package frc2168_2013.commands;

/**
 * A command to fire cylinders to achive stow position on arms
 * 
 * @author ICW
 *
 */
public class StowArmPosition extends CommandBase {

	public StowArmPosition() {
		requires (arm);
	}

	protected void initialize() {
		//Nothing to do
	}

	protected void execute() {
		arm.stowPosition();
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
