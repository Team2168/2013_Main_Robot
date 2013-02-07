
package frc2168_2013.commands;

/**
 *
 * @author Shriji
 */
public class StopperEngage extends CommandBase {

    public StopperEngage() {
    	requires(hopper);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	hopper.engageStopper();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	end();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	hopper.disengageStopper(); //disengaged so frisbee can be shot, much better to let the frisbee go than to have it jammed in the shooter!
    }
}
