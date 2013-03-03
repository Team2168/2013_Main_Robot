package frc2168_2013.commands;

public class DriveDrivetrainWithJoystick extends CommandBase {
	
	private static final double ACCEL_RATE_LIMIT = 0.07;  //Slowly accelerate
	private static final double DECEL_RATE_LIMIT = 0.2;   //Quickly decelerate
	private double leftSpeed, rightSpeed;
	
	public DriveDrivetrainWithJoystick(){
		requires(drivetrain);
	}
	
	protected void initialize() {
		leftSpeed = rightSpeed = 0.0;
	}

	protected void execute() {
		//It is possible to bring the battery to it's knees with the 6 motors
		//  on the drivetrain. Play nice, slowly ramp up.
		leftSpeed = drivetrain.rateLimit(oi.getbaseDriverLeftAxis(), leftSpeed,
				ACCEL_RATE_LIMIT, DECEL_RATE_LIMIT);
		rightSpeed = drivetrain.rateLimit(oi.getbaseDriverRightAxis(), rightSpeed,
				ACCEL_RATE_LIMIT, DECEL_RATE_LIMIT);
		
		drivetrain.tankDrive(rightSpeed, leftSpeed);
	}

	protected boolean isFinished() {
		return false;
	}
	
	protected void interrupted() {
		//It's unlikely this will ever get called.
		//  If this command does end though, we don't want to continue sending
		//  out the last value on the sticks.
		drivetrain.tankDrive(0.0, 0.0);
	}

	protected void end() {
		// nothing to do here
	}
}
