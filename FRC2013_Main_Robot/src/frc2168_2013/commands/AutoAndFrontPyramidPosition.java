package frc2168_2013.commands;

/**
 * A command to engage the hanger mechanism.
 * 
 * @author ICW
 *
 */
public class AutoAndFrontPyramidPosition extends CommandBase {

	public AutoAndFrontPyramidPosition() {
		requires (arm);
	}

	protected void initialize() {
		//Nothing to do
	}

	protected void execute() {
		arm.autoAndFrontPyramid();
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
		return true;
	}
}
