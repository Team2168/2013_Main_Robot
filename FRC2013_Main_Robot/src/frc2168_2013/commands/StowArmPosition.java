package frc2168_2013.commands;

/**
 * Puts the arm in the stow position
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
		return true;
	}
}
