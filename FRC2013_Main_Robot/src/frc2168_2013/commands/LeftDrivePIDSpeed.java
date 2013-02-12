
package frc2168_2013.commands;

/**
 *
 * @author shriji
 */
public class LeftDrivePIDSpeed extends CommandBase {
	
	private double setPoint;

    public LeftDrivePIDSpeed() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(driveTrainL);
    	this.setPoint = driveTrainL.leftSpeedController.getSetPoint();
    }
    
   public LeftDrivePIDSpeed(double setPoint){
	   this();
	   this.setPoint = setPoint;
	   
   }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrainL.leftSpeedController.reset();
    	driveTrainL.leftSpeedController.Enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (setPoint != 0)
    		driveTrainL.leftSpeedController.setSetPoint(setPoint);
    	driveTrainL.driveLeft(driveTrainL.leftSpeedController.getControlOutput());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return driveTrainL.leftSpeedController.isEnabled() == false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	driveTrainL.leftSpeedController.Pause();
    }

    //delete me
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
