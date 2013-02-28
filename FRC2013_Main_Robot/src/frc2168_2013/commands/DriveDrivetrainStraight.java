package frc2168_2013.commands;

public class DriveDrivetrainStraight extends CommandBase {

	private double destDistance; //The goal distance in inches
	private static final double rateLimit = 0.15;
	private double currentLeftSpeed, currentRightSpeed;
	private boolean finished;
	
	/**
	 * Default constructor.
	 * 
	 * @param distance The distance to drive straight in inches
	 */
	public DriveDrivetrainStraight(double distance) {
		requires(drivetrain);
		
		finished = false;
		destDistance = distance;
		currentLeftSpeed = currentRightSpeed = 0.0;
		drivetrain.tankDrive(0, 0);
		drivetrain.resetDistance();
	}
	
	protected void initialize() {
		//nothing special to do here
	}


	/**
	 * Drive straight until we are at our destination
	 */
	protected void execute() {
		double newLeftSpeed, newRightSpeed = 0;

		//if we aren't there yet, set speed
		if(drivetrain.getDistance() < destDistance) {
			newLeftSpeed = newRightSpeed = 0.8;
			
			//TODO: Replace with speed controller
			//Simple rate limiter
			newLeftSpeed = rateLimit(newLeftSpeed, currentLeftSpeed, rateLimit);
			newRightSpeed = rateLimit(newRightSpeed, currentRightSpeed, rateLimit);
			
			//Add in turn based on gyro offset
			
			//output to motors
			drivetrain.tankDrive(newRightSpeed, newLeftSpeed);
			
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

	/**
	 * A simple rate limiter.
	 * http://www.chiefdelphi.com/forums/showpost.php?p=1212189&postcount=3
	 * 
	 * @param input the input value (speed from command/joystick)
	 * @param speed the speed currently being traveled at
	 * @param maxChange the rate limit
	 * @return the new output speed (rate limited)
	 */
	private double rateLimit(double input, double speed, double maxChange) {
		if (input > (speed + maxChange)) {
	        speed = speed + maxChange;
		} else if (input < (speed - maxChange)) {
	        speed = speed - maxChange;
		} else {
	        speed = input;
		}
		
		return speed;
	}
}
