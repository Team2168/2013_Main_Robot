package dashboard;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc2168_2013.RobotMap;
import frc2168_2013.commands.ArmPIDPause;
import frc2168_2013.commands.CommandBase;
import frc2168_2013.commands.DriveArmHomeInit;
import frc2168_2013.commands.Preset_FrontOfPyramid_3pt;
import frc2168_2013.commands.Preset_RearOfPyramid_3pt;
import frc2168_2013.commands.StopShooterWheel;

public class CompetitionSmartDashboard extends CommandBase
{
	
	public CompetitionSmartDashboard()
	{
        //Show what command your subsystem is running on the SmartDashboard
        SmartDashboard.putData(drivetrain);
        SmartDashboard.putData(arm);
        SmartDashboard.putData(shooter);
        SmartDashboard.putData(hopper);
        SmartDashboard.putData(hardStop);
        

		
		//show shooter gains       
		SmartDashboard.putNumber("Shooter P", RobotMap.shooterSpeedP);
		SmartDashboard.putNumber("Shooter I", RobotMap.shooterSpeedI);
		SmartDashboard.putNumber("Shooter D", RobotMap.shooterSpeedD);
		
		//show arm gains
		SmartDashboard.putNumber("Arm P", RobotMap.armPosP);
		SmartDashboard.putNumber("Arm I", RobotMap.armPosI);
		SmartDashboard.putNumber("Arm D", RobotMap.armPosD);
		
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
		SmartDashboard.putNumber("Shooter Encoder", shooter.shooterWheelSpeedController.getSensorRate());
		SmartDashboard.putNumber("Shooter Controller Output", shooter.shooterWheelSpeedController.getControlOutput());
		//SmartDashboard.getBoolean("atSpeed", shooter.shooterWheelSpeedController.atSpeed());
		SmartDashboard.putBoolean("Shooter Enable", shooter.shooterWheelSpeedController.isEnabled());
		SmartDashboard.putNumber("Shooter Execution Time", shooter.shooterWheelSpeedController.getExecutionTime());
		SmartDashboard.putNumber("Shooter SetPoint", shooter.shooterWheelSpeedController.getSetPoint());
		
		SmartDashboard.putNumber("Shooter Error", shooter.shooterWheelSpeedController.getError());
		SmartDashboard.putNumber("Shooter Accept Error", shooter.shooterWheelSpeedController.getAcceptErrorDiff());
		
		//////////////////////////////////////////Arm/////////////////////////////////////////////////////////////////
		
		//put Arm encoder data on screen
		SmartDashboard.putNumber("Arm Encoder", arm.armPosController.getSensorRate());
		SmartDashboard.putNumber("Arm Controller Output", arm.armPosController.getControlOutput());
		//SmartDashboard.getBoolean("atSpeed", arm.armPosController.atSpeed());
		SmartDashboard.putBoolean("Arm Enable", arm.armPosController.isEnabled());
		SmartDashboard.putNumber("Arm Execution Time", arm.armPosController.getExecutionTime());
		SmartDashboard.putNumber("Arm SetPoint", arm.armPosController.getSetPoint());
		
		SmartDashboard.putNumber("Arm Error", arm.armPosController.getError());
		SmartDashboard.putNumber("Arm Accept Error", arm.armPosController.getAcceptErrorDiff());
		
		//////////////////////////////////Commands///////////////////////////////////////////////////////////////////////
		
		SmartDashboard.putData("Stop Arm PID", new ArmPIDPause());
		SmartDashboard.putData("Drive Arm Home", new DriveArmHomeInit());
		SmartDashboard.putData("Preset Front Of Pyramid", new Preset_FrontOfPyramid_3pt());
		SmartDashboard.putData("Preset Back Of Pyramid", new Preset_RearOfPyramid_3pt());
		SmartDashboard.putData("Stop Shooter PID", new StopShooterWheel());
		
		
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
