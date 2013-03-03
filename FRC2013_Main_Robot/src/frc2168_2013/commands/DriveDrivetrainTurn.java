package frc2168_2013.commands;

import frc2168_2013.RobotMap;

public class DriveDrivetrainTurn extends CommandBase {

	private double destinationAngle = 0; //The goal distance in inches
	private double currentAngle = 0;
	private boolean finished;
	
	private static final double ROTATION_SPEED = 0.7; //(1.0 to -1.0)
	private static final double ERROR = 5.0; //acceptable positional error in deg.
	
	/**
	 * Default constructor.
	 * 
	 * @param distance The angle to turn, in degrees (positive is clockwise)
	 */
	public DriveDrivetrainTurn(double angle) {
		requires(drivetrain);
		destinationAngle = angle;
	}
	
	protected void initialize() {
		finished = false;
		
		drivetrain.tankDrive(0, 0);
		drivetrain.resetAngle();
	}


	/**
	 * Drive straight until we are at our destination
	 */
	protected void execute() {
		System.out.println("Gyro: " + drivetrain.getAngle());
		
		//Positive angle is rotation clockwise, negative is counter-clockwise
		if (Math.abs(currentAngle - destinationAngle) < ERROR) {
			//We're there
			finished = true;
		} else if (destinationAngle > drivetrain.getAngle()) {
			//rotate clockwise
			drivetrain.tankDrive(-ROTATION_SPEED, ROTATION_SPEED);
		} else {
			//rotate counterclockwise
			drivetrain.tankDrive(ROTATION_SPEED, -ROTATION_SPEED);
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
