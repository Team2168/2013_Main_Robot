
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
    	this.setPoint = driveTrain.rightSpeedController.getSetPoint();
    }
    
   public DrivePIDSpeed(double setPoint){
	   this();
	   this.setPoint = setPoint;
	   
   }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.rightSpeedController.reset();
    	driveTrain.rightSpeedController.Enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (setPoint != 0)
    		driveTrain.rightSpeedController.setSetPoint(setPoint);
    	driveTrain.driveRight(driveTrain.rightSpeedController.getControlOutput());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return driveTrain.rightSpeedController.isEnabled() == false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	driveTrain.rightSpeedController.Pause();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
