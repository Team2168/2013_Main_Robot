
package frc2168_2013.commands;

import frc2168_2013.RobotMap;

/**
 * Drive the hopper until we see a frisbee right before the shooter.
 *
 * @author Shriji
 */
public class DriveUntilFrisbee extends CommandBase {
	boolean frisbee;

	/**
	 * Default constructor.
	 */
    public DriveUntilFrisbee() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(hopper);
    	frisbee = false;
    }

    /**
     *  Called just before this Command runs the first time
     */
    protected void initialize() {
    }

    /**
     * Called repeatedly when this Command is scheduled to run
     */
    protected void execute() {
    	if(hopper.disc1Present()) {		//If a Frisbee is present at the last
    									//  position before the shooter.
			hopper.driveHopper(0.0);	//Stop the hopper
			frisbee = true;
		} else {											//If a Frisbee isn't present,
			hopper.driveHopper(RobotMap.hopperMotor);	//Drive the hopper
			frisbee = false;
		}
    }

    /**
     * This ends the command when a frisbee is present.
     */
    protected boolean isFinished() {
        return frisbee;
    }

    /**
     * Called once after isFinished returns true.
     */
    protected void end() {
    	hopper.driveHopper(0.0);
    }

    /**
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run.
     */
    protected void interrupted() {
    	end(); //Stop the hopper before this command completes.
    }
}
