
package frc2168_2013.commands;

import frc2168_2013.RobotMap;

/**
 *
 * @author Shriji
 */
public class DriveUntilFrisbee extends CommandBase {
	
	boolean frisbee;

    public DriveUntilFrisbee() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(hopper);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    boolean switchPressed;

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	switchPressed = hopper.disc1Present();
    	if(switchPressed = true){	//if a frisbee is present at the last checkpoint before contacting shooter
			hopper.driveHopperPWM(0.0);			//stop the hopper
			frisbee = true;
		} else{												//if a frisbee isn't present
			hopper.driveHopperPWM(RobotMap.hopperMotor);	//drive up
			frisbee = false;
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return frisbee;
    }

    // Called once after isFinished returns true
    protected void end() {
    	hopper.driveHopperPWM(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
