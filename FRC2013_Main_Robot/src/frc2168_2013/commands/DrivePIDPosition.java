
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
    	requires(driveTrainL);
    	this.setPoint = driveTrainL.leftPosController.getSetPoint();
    	
    }

    public DrivePIDPosition(double setPoint){
 	   this();
 	   this.setPoint = setPoint;
 	   
    }

    //delete me
    
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrainL.leftPosController.reset();
    	driveTrainL.leftPosController.Enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    		driveTrainL.leftPosController.setSetPoint(setPoint);
    	driveTrainL.driveLeft(driveTrainL.leftPosController.getControlOutput());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return driveTrainL.leftPosController.isEnabled() == true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	driveTrainL.leftPosController.Pause();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
