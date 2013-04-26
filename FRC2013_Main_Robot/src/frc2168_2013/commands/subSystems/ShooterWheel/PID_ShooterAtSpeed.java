package frc2168_2013.commands.subSystems.ShooterWheel;

import frc2168_2013.commands.CommandBase;

public class PID_ShooterAtSpeed extends CommandBase
{
	private int count = 0;
	private volatile double[] atSpeed;
	private int sp;
	
	public PID_ShooterAtSpeed() {
		//no need to require the subsystem
		//the PID controller owns the subsystem
		
		this.atSpeed = new double[50];
		
		this.sp = 110;
		
	}


	protected void initialize() {
		// TODO Auto-generated method stub


	}


	protected void execute() {
		// TODO Auto-generated method stub

	}


	protected boolean isFinished() {
		// TODO Auto-generated method stub
		
			//finish is based on verifying the voltage is constant over some length of time
			if (count==this.atSpeed.length)
				count=0;
			
			atSpeed[count]=shooterWheel.shooterWheelSpeedControllerFwd.getSensorRate();
			count++;
			
			int inRange=0;
			for(int j=1; j<atSpeed.length; j++)
			{
				if ((atSpeed[j]>= this.sp-10) )
					inRange++;
				else
					inRange=0;
			}
			if (inRange==atSpeed.length-1)
				return true;
			return false;

	}


	protected void end() {
		// TODO Auto-generated method stub

	}


	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

}