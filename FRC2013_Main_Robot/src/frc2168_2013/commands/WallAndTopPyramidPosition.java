package frc2168_2013.commands;

/**
 * Moves the arm to the highest position
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
		return true;
	}
}
