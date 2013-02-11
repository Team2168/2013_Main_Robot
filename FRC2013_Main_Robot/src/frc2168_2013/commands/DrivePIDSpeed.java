
package frc2168_2013.commands;

/**
 *
 * @author shriji
 */
public class DrivePIDSpeed extends CommandBase {
	
	private double setPoint;

    public DrivePIDSpeed() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(driveTrain);
    	this.setPoint = driveTrain.leftSpeedController.getSetPoint();
    }
    
   public DrivePIDSpeed(double setPoint){
	   this();
	   this.setPoint = setPoint;
	   
   }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.leftSpeedController.reset();
    	driveTrain.leftSpeedController.Enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (setPoint != 0)
    		driveTrain.leftSpeedController.setSetPoint(setPoint);
    	driveTrain.driveLeft(driveTrain.leftSpeedController.getControlOutput());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return driveTrain.leftSpeedController.isEnabled() == false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	driveTrain.leftSpeedController.Pause();
    	this.cancel();
    }

    //delete me
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
