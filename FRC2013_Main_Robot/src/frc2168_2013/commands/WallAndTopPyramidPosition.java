package frc2168_2013.commands;

/**
 * A command to engage the hanger mechanism.
 * 
 * @author ICW
 *
 */
public class WallAndTopPyramidPosition extends CommandBase {

	public WallAndTopPyramidPosition() {
		requires (arm);
	}

	protected void initialize() {
		//Nothing to do
	}

	protected void execute() {
		arm.wallAndTopPyramid();
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
