
package frc2168_2013.commands;

import frc2168_2013.RobotMap;

/**
 * Drive the hopper until all the frisbees are present.
 *
 * @author Shriji
 */
public class StopHopperWhenFull extends CommandBase {

	/**
	 * Default constructor.
	 */
    public StopHopperWhenFull() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(hopper);
    	requires(hardStop);
    }

    /**
     *  Called just before this Command runs the first time
     */
    protected void initialize() {
    	hardStop.engageStopper();
    }

    /**
     * Called repeatedly when this Command is scheduled to run
     */
    protected void execute() {
    	if(hopper.getNumberOfDiscs() >= 3) {	//If Frisbees are present at all the sensors
			hopper.driveHopperPWM(0.0);	//Stop the hopper
			
		} else {											
			hopper.driveHopperPWM(RobotMap.hopperMotor);	//else, Drive the hopper
		}
    }

    /**
     * This ends the command when a frisbee is present.
     */
    protected boolean isFinished() {
        return true;
    }

    /**
     * Called once after isFinished returns true.
     */
    protected void end() {
    }

    /**
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run.
     */
    protected void interrupted() {
    	end(); //Stop the hopper before this command completes.
    }
}
