
package frc2168_2013.commands.subSystems.Hanger;

import frc2168_2013.RobotMap;
import frc2168_2013.commands.CommandBase;

/**
 * Drives the hopper until there isn't a disk at the end before the shooter.
 * Typically this is because it's just been shot.
 *
 * @author Shriji
 */
public class DriveUntilNoFrisbee extends CommandBase {
	boolean frisbee;
	
	/**
	 * Default constructor.
	 */
    public DriveUntilNoFrisbee() {
    	requires(hopper);
    	frisbee = false;
    }

    /**
     * Called just before this Command runs the first time.
     */
    protected void initialize() {
    }

    /**
     * Called repeatedly when this Command is scheduled to run.
     */
    protected void execute() {
    	if(hopper.disc1Present()) {
        	//Drive the hopper if a frisbee is present at the last disc
    		//  position before contacting shooter.
			hopper.driveHopperPWM(RobotMap.hopperVoltage);
			frisbee = true;
		} else {
			//Otherwise stop the hopper motor
			hopper.driveHopperPWM(0.0);
			frisbee = false;
		}
    }
    
    /**
     * The command ends when there isn't a disc present at the end of the hopper.
     */
    protected boolean isFinished() {
        return !frisbee;
    }

    /**
     * Called once after isFinished returns true
     */
    protected void end() {
    }

    /**
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run
     */
    protected void interrupted() {
    	//If we are interrupted, stop the hopper motor.
    	hopper.driveHopperPWM(0.0);
    }
}
