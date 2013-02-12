package frc2168_2013.commands;

import edu.wpi.first.wpilibj.command.Command;

public class PID_PositionRight extends CommandBase {
	
	private double setPoint;
	
	public PID_PositionRight()
	{
		requires(driveTrain);
		this.setPoint = 0;
	}
	
	public PID_PositionRight(double setPoint)
	{
		this();
		this.setPoint = setPoint;
	}
	
	protected void initialize() {
		// TODO Auto-generated method stub
		driveTrain.rightPosController.reset();
		driveTrain.rightPosController.Enable();
	}
	
	protected void execute() {
		// TODO Auto-generated method stub
		driveTrain.rightPosController.setSetPoint(this.setPoint);
		driveTrain.driveRight(driveTrain.rightPosController.getControlOutput());

	}
	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void end() {
		// TODO Auto-generated method stub
		driveTrain.rightPosController.Pause();
		this.cancel();

	}

	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}



}