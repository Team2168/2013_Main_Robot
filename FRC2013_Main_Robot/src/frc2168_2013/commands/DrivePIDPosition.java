
package frc2168_2013.commands;

/**
 *
 * @author shriji
 */
public class DrivePIDPosition extends CommandBase {

	private double setPoint;
	
    public DrivePIDPosition() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(driveTrain);
    	this.setPoint = driveTrain.leftPosController.getSetPoint();
    	
    }

    public DrivePIDPosition(double setPoint){
 	   this();
 	   this.setPoint = setPoint;
 	   
    }

    
    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.leftPosController.reset();
    	driveTrain.leftPosController.Enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    		driveTrain.leftPosController.setSetPoint(setPoint);
    	driveTrain.driveLeft(driveTrain.leftPosController.getControlOutput());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return driveTrain.leftPosController.isEnabled() == false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	driveTrain.leftPosController.Pause();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
