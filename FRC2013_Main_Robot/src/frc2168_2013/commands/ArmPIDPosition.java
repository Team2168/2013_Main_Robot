
package frc2168_2013.commands;

/**
 *
 * @author shriji
 */
public class ArmPIDPosition extends CommandBase {

	private double setPoint;
	
    public ArmPIDPosition() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(arm);
    	this.setPoint = arm.armPosController.getSetPoint();
    	
    }

    public ArmPIDPosition(double setPoint){
 	   this();
 	   this.setPoint = setPoint;
 	   
    }

    //delete me
    
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	arm.armPosController.reset();
    	arm.armPosController.Enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	arm.armPosController.setSetPoint(setPoint);
    	arm.driveArm(arm.armPosController.getControlOutput());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return arm.armPosController.isEnabled() == false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	arm.armPosController.Pause();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
