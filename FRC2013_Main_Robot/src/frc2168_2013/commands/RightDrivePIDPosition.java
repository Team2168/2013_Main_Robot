
package frc2168_2013.commands;

/**
 *
 * @author shriji
 */
public class RightDrivePIDPosition extends CommandBase {

	private double setPoint;
	
    public RightDrivePIDPosition() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(driveTrainR);
    	this.setPoint = driveTrainR.rightPosController.getSetPoint();
    	
    }

    public RightDrivePIDPosition(double setPoint){
 	   this();
 	   this.setPoint = setPoint;
 	   
    }

    //delete me
    
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrainR.rightPosController.reset();
    	driveTrainR.rightPosController.Enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    		driveTrainR.rightPosController.setSetPoint(setPoint);
    	driveTrainR.driveRight(driveTrainR.rightPosController.getControlOutput());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return driveTrainR.rightPosController.isEnabled() == true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	driveTrainR.rightPosController.Pause();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
