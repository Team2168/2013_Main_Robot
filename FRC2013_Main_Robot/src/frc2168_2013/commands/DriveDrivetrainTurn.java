package frc2168_2013.commands;

import frc2168_2013.RobotMap;

public class DriveDrivetrainTurn extends CommandBase {

	private double desAngle = 0; //The goal distance in inches
	private double actAngle = 0;
	private boolean finished;
	private double angleRange;
	
	/**
	 * Default constructor.
	 * 
	 * @param distance The angle to turn, in radians(i think?)
	 */
	public DriveDrivetrainTurn(double angle) {
		requires(drivetrain);
		
		finished = false;
		desAngle = angle;
		angleRange = RobotMap.angleValRange;
		drivetrain.tankDrive(0, 0);
		drivetrain.resetAngle();
	}
	
	protected void initialize() {
		//nothing special to do here
	}


	/**
	 * Drive straight until we are at our destination
	 */
	protected void execute() {
			
			desAngle = drivetrain.getAngle();			//assuming clockwise is positive, assuming range in robotmap is good.
			
			while ((desAngle < (actAngle - angleRange)) || (desAngle > (actAngle + angleRange))){		//range is currently set at 2.5
				
				if (desAngle > 0){
					
					drivetrain.tankDrive(0.10, 0.00);
					
				} else if (desAngle < 0){
					
					drivetrain.tankDrive(0.00, 0.10);
					
				} else {
					
					drivetrain.tankDrive(0.00, 0.00);
				
				}
				
				desAngle = drivetrain.getAngle();
			}
			
			if ((desAngle < (actAngle - angleRange)) || (desAngle > (actAngle + angleRange))){
				
				finished = true;
				
			} else {
				
				finished = false;
				
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
