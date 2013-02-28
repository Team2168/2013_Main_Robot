package dashboard;

import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;
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
		SmartDashboard.putDouble("P", RobotMap.shooterSpeedP);
		SmartDashboard.putDouble("I", RobotMap.shooterSpeedI);
		SmartDashboard.putDouble("D", RobotMap.shooterSpeedD);
		
		//show arm gains
		SmartDashboard.putDouble("P", RobotMap.armPosP);
		SmartDashboard.putDouble("I", RobotMap.armPosI);
		SmartDashboard.putDouble("D", RobotMap.armPosD);
		
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
		SmartDashboard.putDouble("Controller Output", shooter.shooterWheelSpeedController.getControlOutput());
		//SmartDashboard.getBoolean("atSpeed", shooter.shooterWheelSpeedController.atSpeed());
		SmartDashboard.putBoolean("Enable", shooter.shooterWheelSpeedController.isEnabled());
		SmartDashboard.putDouble("Execution Time", shooter.shooterWheelSpeedController.getExecutionTime());
		SmartDashboard.putDouble("Shooter SetPoint", shooter.shooterWheelSpeedController.getSetPoint());
		
		SmartDashboard.putDouble("shooter Error", shooter.shooterWheelSpeedController.getError());
		SmartDashboard.putDouble("shooter Accept Error", shooter.shooterWheelSpeedController.getAcceptErrorDiff());
		
		//////////////////////////////////////////Arm/////////////////////////////////////////////////////////////////
		
		//put Arm encoder data on screen
		SmartDashboard.putDouble("Shooter Encoder", arm.armPosController.getSensorRate());
		SmartDashboard.putDouble("Controller Output", arm.armPosController.getControlOutput());
		//SmartDashboard.getBoolean("atSpeed", arm.armPosController.atSpeed());
		SmartDashboard.putBoolean("Enable", arm.armPosController.isEnabled());
		SmartDashboard.putDouble("Execution Time", arm.armPosController.getExecutionTime());
		SmartDashboard.putDouble("Shooter SetPoint", arm.armPosController.getSetPoint());
		
		SmartDashboard.putDouble("shooter Error", arm.armPosController.getError());
		SmartDashboard.putDouble("shooter Accept Error", arm.armPosController.getAcceptErrorDiff());
		
		
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
