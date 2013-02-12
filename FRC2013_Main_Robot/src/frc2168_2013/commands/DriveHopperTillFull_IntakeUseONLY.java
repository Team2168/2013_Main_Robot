
package frc2168_2013.commands;

import frc2168_2013.RobotMap;

/**
 *
 * @author shriji
 */
public class DriveHopperTillFull_IntakeUseONLY extends CommandBase {

	boolean frisbee;
	
    public DriveHopperTillFull_IntakeUseONLY() {
    	requires(hopper);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    boolean lastSwitch_Hopper;
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	lastSwitch_Hopper = hopper.disc4Present();
    	if(lastSwitch_Hopper = true){	//if a frisbee is present at the last switch on hopper
			hopper.driveHopper(0.0);			//stop hopper
			frisbee = true;
		} else{												//if a frisbee isn't present
			hopper.driveHopper(RobotMap.hopperVoltage);	//drive till present
			frisbee = false;
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return frisbee;
    }

    // Called once after isFinished returns true
    protected void end() {
    	end();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	hopper.driveHopper(0.0);
    }
}
