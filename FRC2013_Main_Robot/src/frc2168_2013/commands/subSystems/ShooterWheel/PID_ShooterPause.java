
package frc2168_2013.commands.subSystems.ShooterWheel;

import frc2168_2013.commands.CommandBase;

/**
 *
 * @author shriji
 */
public class PID_ShooterPause extends CommandBase {

    public PID_ShooterPause() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(shooterWheel);
    }

    // Called just before this Command runs the first time
   
	protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
  
	protected void execute() {
    	shooterWheel.shooterWheelSpeedControllerAft.Pause();
    	shooterWheel.shooterWheelSpeedControllerFwd.Pause();
    	
    }

    //delete me
    // Make this return true when this Command no longer needs to run execute()
    
	protected boolean isFinished() {
        return (shooterWheel.shooterWheelSpeedControllerAft.isEnabled() == false && shooterWheel.shooterWheelSpeedControllerFwd.isEnabled() == false);
    }

    // Called once after isFinished returns true
    
	protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    
	protected void interrupted() {
    }
}
