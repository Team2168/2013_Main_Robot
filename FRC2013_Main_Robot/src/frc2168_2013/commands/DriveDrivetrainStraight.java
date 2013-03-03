package frc2168_2013.commands;

import frc2168_2013.OI;

public class DriveDrivetrainStraight extends CommandBase {

	private double destDistance; //The goal distance in inches
	private static final double rateLimit = 0.01;
	private double currentLeftSpeed, currentRightSpeed;
	private boolean finished;
	
	/**
	 * Default constructor.
	 * 
	 * @param distance The distance to drive straight in inches
	 */
	public DriveDrivetrainStraight(double distance) {
		requires(drivetrain);
		
		destDistance = distance;
	}
	
	protected void initialize() {
		finished = false;
		currentLeftSpeed = currentRightSpeed = OI.minDriveSpeed;
		drivetrain.tankDrive(0, 0);
		drivetrain.resetDistance();
		drivetrain.resetAngle();
	}


	/**
	 * Drive straight until we are at our destination.
	 * This only travels forwards right now.
	 */
	protected void execute() {
		double newLeftSpeed = 0, newRightSpeed = 0, angle = 0;
		double speedModifierL = 1, speedModifierR = 1;

		//TODO: allow reverse moves! add a multiplier to flip sign 
		
		//if we aren't there yet, set speed
		if(drivetrain.getDistance() < destDistance) {
			newLeftSpeed = newRightSpeed = 0.4;
			
			//TODO: Replace with speed controller
			//Simple rate limiter
			newLeftSpeed = drivetrain.rateLimit(newLeftSpeed, currentLeftSpeed, rateLimit);
			newRightSpeed = drivetrain.rateLimit(newRightSpeed, currentRightSpeed, rateLimit);
			
			//Add in turn based on gyro offset (+/-1 deg deadband)
			angle = drivetrain.getAngle();			//assuming clockwise is positive, 10% increment in speed
			if (angle > 1){							//can make modifier use function(angular displacement)
				//increase right speed to turn to the left
				speedModifierR = speedModifierR * 1.10;
			} else if (angle < -1){
				//increase left speed to turn to the right
				speedModifierL = speedModifierL * 1.10;			
			} else {
				//Continue driving straight
				speedModifierR = 1;
				speedModifierL = 1;
			}
			
			//output to motors
			drivetrain.tankDrive((speedModifierR * newRightSpeed), (speedModifierL * newLeftSpeed));
			
			currentLeftSpeed = newLeftSpeed;
			currentRightSpeed = newRightSpeed;
		} else {
			//we are there, stop
			drivetrain.tankDrive(0.0, 0.0);
			finished = true;
		}
	}

	
	protected boolean isFinished() {
		return finished;
	}

	
	protected void end() {
		//make sure we are stopped for good measure
		drivetrain.tankDrive(0, 0);
	}

	
	protected void interrupted() {
		//Clear the current command to motor controllers if we're interrupted.
		drivetrain.tankDrive(0, 0);
	}
}
