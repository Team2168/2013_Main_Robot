package frc2168_2013.commands;

public class DriveDrivetrainWithJoystick extends CommandBase {
	
	public DriveDrivetrainWithJoystick(){
		requires(drivetrain);
	}

	protected void end() {
		// TODO Auto-generated method stub
	}

	protected void execute() {
		drivetrain.tankDrive(oi.getbaseDriverRightAxis(), oi.getbaseDriverLeftAxis());
	}

	protected void initialize() {
		// TODO Auto-generated method stub
	}

	protected void interrupted() {
		// TODO Auto-generated method stub
	}

	protected boolean isFinished() {
		return false;
	}

}
