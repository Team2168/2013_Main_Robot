
package frc2168_2013.commands;

/**
 *
 * @author shriji
 */
public class DrivePIDPause extends CommandBase {

    public DrivePIDPause() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.rightPosController.Pause();
    	driveTrain.rightSpeedController.Pause();
    	driveTrain.leftPosController.Pause();
    	driveTrain.leftSpeedController.Pause();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (driveTrain.rightPosController.isEnabled() == false) && (driveTrain.rightSpeedController.isEnabled() == false) && (driveTrain.leftSpeedController.isEnabled() == false) && (driveTrain.leftPosController.isEnabled() == false);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
