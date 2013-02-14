
package frc2168_2013.commands;

/**
 *
 * @author shriji
 */
public class ShooterPIDPause extends CommandBase {

    public ShooterPIDPause() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shooter.shooterWheelSpeedController.Pause();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    //delete me
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (shooter.shooterWheelSpeedController.isEnabled() == false);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
