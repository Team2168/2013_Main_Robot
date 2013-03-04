package dashboard;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc2168_2013.RobotMap;
import frc2168_2013.commands.CommandBase;

public class CompetitionDashboard extends CommandBase
{
	
	public CompetitionDashboard()
	{
        //Show what command your subsystem is running on the SmartDashboard
        SmartDashboard.putData(drivetrain);
        SmartDashboard.putData(arm);
        SmartDashboard.putData(shooter);
        SmartDashboard.putData(hopper);
        SmartDashboard.putData(hardStop);
        

		
		//show shooter gains       
		SmartDashboard.putDouble("Shooter P", RobotMap.shooterSpeedP);
		SmartDashboard.putDouble("Shooter I", RobotMap.shooterSpeedI);
		SmartDashboard.putDouble("Shooter D", RobotMap.shooterSpeedD);
		
		//show arm gains
		SmartDashboard.putDouble("Arm P", RobotMap.armPosP);
		SmartDashboard.putDouble("Arm I", RobotMap.armPosI);
		SmartDashboard.putDouble("Arm D", RobotMap.armPosD);
		
		//add clock
		SmartDashboard.putString("compTime", "");
	
		
	}

	
	protected void initialize()
	{
		// TODO Auto-generated method stub

	}

	

	protected void execute()
	{
		/////////////////////////////////////////Shooter//////////////////////////////////////////////////////////
		
		// TODO Auto-generated method stub
		//put shooter encoder data on screen
		SmartDashboard.putDouble("Shooter Encoder", shooter.shooterWheelSpeedController.getSensorRate());
		SmartDashboard.putDouble("Shooter Controller Output", shooter.shooterWheelSpeedController.getControlOutput());
		//SmartDashboard.getBoolean("atSpeed", shooter.shooterWheelSpeedController.atSpeed());
		SmartDashboard.putBoolean("Shooter Enable", shooter.shooterWheelSpeedController.isEnabled());
		SmartDashboard.putDouble("Shooter Execution Time", shooter.shooterWheelSpeedController.getExecutionTime());
		SmartDashboard.putDouble("Shooter SetPoint", shooter.shooterWheelSpeedController.getSetPoint());
		
		SmartDashboard.putDouble("Shooter Error", shooter.shooterWheelSpeedController.getError());
		SmartDashboard.putDouble("Shooter Accept Error", shooter.shooterWheelSpeedController.getAcceptErrorDiff());
		
		//////////////////////////////////////////Arm/////////////////////////////////////////////////////////////////
		
		//put Arm encoder data on screen
		SmartDashboard.putDouble("Arm Encoder", arm.armPosController.getSensorRate());
		SmartDashboard.putDouble("Arm Controller Output", arm.armPosController.getControlOutput());
		//SmartDashboard.getBoolean("atSpeed", arm.armPosController.atSpeed());
		SmartDashboard.putBoolean("Arm Enable", arm.armPosController.isEnabled());
		SmartDashboard.putDouble("Arm Execution Time", arm.armPosController.getExecutionTime());
		SmartDashboard.putDouble("Arm SetPoint", arm.armPosController.getSetPoint());
		
		SmartDashboard.putDouble("Arm Error", arm.armPosController.getError());
		SmartDashboard.putDouble("Arm Accept Error", arm.armPosController.getAcceptErrorDiff());
		
		
	}

	
	protected boolean isFinished()
	{
		// TODO Auto-generated method stub
		return false;
	}

	
	protected void end()
	{
		// TODO Auto-generated method stub

	}

	
	protected void interrupted()
	{
		// TODO Auto-generated method stub

	}

}
