package frc2168_2013.commands;

public class DriveDrivetrainStraight extends CommandBase {

	private double destDistance; //The goal distance in inches
	private static final double rateLimit = 0.05;
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
		drivetrain.resetAngle();
	}
	
	protected void initialize() {
		//nothing special to do here
	}


	/**
	 * Drive straight until we are at our destination
	 */
	protected void execute() {
		double newLeftSpeed = 0, newRightSpeed = 0, angle = 0;
		double speedModifierL = 1, speedModifierR = 1;

		//if we aren't there yet, set speed
		if(drivetrain.getDistance() < destDistance) {
			newLeftSpeed = newRightSpeed = 0.5;
			
			//TODO: Replace with speed controller
			//Simple rate limiter
			newLeftSpeed = rateLimit(newLeftSpeed, currentLeftSpeed, rateLimit);
			newRightSpeed = rateLimit(newRightSpeed, currentRightSpeed, rateLimit);
			
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
