
package frc2168_2013.commands;

/**
 *
 * @author shriji
 */
public class ArmPIDPause extends CommandBase {

    public ArmPIDPause() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	arm.armPosController.Pause();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    //delete me
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (arm.armPosController.isEnabled() == false);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
