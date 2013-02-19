
package frc2168_2013.commands;

/**
 *
 * @author Shriji
 */
public class StopShooterWheel extends CommandBase {
    /**
     * Default constructor.
     */
	public StopShooterWheel() {
    	requires(shooter);
    }

    /**
     * Called just before this Command runs the first time.
     */
    protected void initialize() {
    	//Nothing special to do on initialization.
    }

    /**
     * Called repeatedly when this Command is scheduled to run.
     */
    protected void execute() {
    	shooter.setShooterPWM(0.0);
    }

    /**
     * Returns true after the wheel has been stopped.
     */
    protected boolean isFinished() {
        return true;
    }

    /**
     * Called once after isFinished returns true
     */
    protected void end() {
    	//The wheel is stopped, nothing special needs to be done on command completion.
    }

    /**
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run.
     */
    protected void interrupted() {
    	//The wheel is stopped, nothing special to do if interrupted.
    }
}
