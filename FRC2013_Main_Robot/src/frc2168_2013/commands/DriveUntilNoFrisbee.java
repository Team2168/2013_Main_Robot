
package frc2168_2013.commands;

import frc2168_2013.RobotMap;

/**
 *
 * @author Shriji
 */
public class DriveUntilNoFrisbee extends CommandBase {

	boolean frisbee;
	
    public DriveUntilNoFrisbee() {
    	requires(hopper);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    boolean switchPressed;

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	switchPressed = hopper.disc1Present();
    	if(switchPressed = true){	//if a frisbee is present at the last checkpoint before contacting shooter
			hopper.driveHopperPWM(RobotMap.hopperVoltage);			//drive to shoot it
			frisbee = true;
		} else{												//if a frisbee isn't present
			hopper.driveHopperPWM(0.0);	//stop hopper
			frisbee = false;
		}
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !frisbee;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
