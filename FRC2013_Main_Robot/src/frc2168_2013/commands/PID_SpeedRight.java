package frc2168_2013.commands;

import edu.wpi.first.wpilibj.command.Command;

public class PID_SpeedRight extends CommandBase {
	
	private double setPoint;
	
	public PID_SpeedRight()
	{
		requires(driveTrain);
		this.setPoint = 0;
	}
	
	public PID_SpeedRight(double setPoint)
	{
		this();
		this.setPoint = setPoint;
	}
	
	protected void initialize() {
		// TODO Auto-generated method stub
		driveTrain.rightSpeedController.reset();
		driveTrain.rightSpeedController.Enable();
	}
	
	protected void execute() {
		// TODO Auto-generated method stub
		driveTrain.rightSpeedController.setSetPoint(this.setPoint);
		driveTrain.driveRight(driveTrain.rightSpeedController.getControlOutput());

	}
	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void end() {
		// TODO Auto-generated method stub
		driveTrain.rightSpeedController.Pause();
		this.cancel();

	}

	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}



}
