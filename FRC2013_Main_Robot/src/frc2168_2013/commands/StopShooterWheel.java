
package frc2168_2013.commands;

/**
 *
 * @author Shriji
 */
public class StopShooterWheel extends CommandBase {
	boolean shit; //just had to make a bollean that would not be used so i dont have to add timeout to commandgroup

    public StopShooterWheel() {
    	requires(shooter);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shooter.setPWM(0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return shit;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	shooter.setPWM(0);
    }
}
