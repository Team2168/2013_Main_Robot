
package frc2168_2013.commands.subSystems.Shooter;

import frc2168_2013.commands.CommandBase;

/**
 *
 * @author shriji
 */
public class ShooterPIDSpeed extends CommandBase {
	
	private double setPoint;

    public ShooterPIDSpeed() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(shooter);
    	this.setPoint = shooter.shooterWheelSpeedController.getSetPoint();
    }
    
   public ShooterPIDSpeed(double setPoint){
	   this();
	   this.setPoint = setPoint;
	   
   }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shooter.shooterWheelSpeedController.reset();
    	shooter.shooterWheelSpeedController.Enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (setPoint != 0)
    		shooter.shooterWheelSpeedController.setSetPoint(setPoint);
    	shooter.setShooterPWM(shooter.shooterWheelSpeedController.getControlOutput());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return shooter.shooterWheelSpeedController.isEnabled() == false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	shooter.shooterWheelSpeedController.Pause();
    }

    //delete me
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
