package frc2168_2013.dashboard;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc2168_2013.CommandBaseRobot;
import frc2168_2013.RobotMap;
import frc2168_2013.commands.CommandBase;

public class CompetitionDashboard extends CommandBase
{

public CompetitionDashboard()
{
        //Show what command your subsystem is running on the SmartDashboard
        SmartDashboard.putData(drivetrain);
        SmartDashboard.putData(shooterWheel);
        SmartDashboard.putData(shooterAngle);
        SmartDashboard.putData(arm);
        SmartDashboard.putData(hopper);
        SmartDashboard.putData(hanger);
        


//show shooter gains
//SmartDashboard.putNumber("P", RobotMap.shooterP);
//SmartDashboard.putNumber("I", RobotMap.shooterI);
//SmartDashboard.putNumber("D", RobotMap.shooterD);

//add clock
SmartDashboard.putString("compTime", "");


}



protected void initialize()
{
// TODO Auto-generated method stub

}



protected void execute()
{
// TODO Auto-generated method stub
//put Shooter data on screen
SmartDashboard.putNumber("shooterEncoder", shooterWheel.shooterWheelSpeedControllerAft.getSensorRate());
SmartDashboard.putNumber("shooter PID Output", shooterWheel.shooterWheelSpeedControllerAft.getControlOutput());
SmartDashboard.putBoolean("shooterAtSpeed", shooterWheel.shooterWheelSpeedControllerAft.isFinished());
SmartDashboard.putBoolean("enable", shooterWheel.shooterWheelSpeedControllerAft.isEnabled());
SmartDashboard.putNumber("executionTime", shooterWheel.shooterWheelSpeedControllerAft.getExecutionTime());
SmartDashboard.putNumber("shooterSetPoint", shooterWheel.shooterWheelSpeedControllerAft.getSetPoint());



SmartDashboard.putNumber("shooter Err", shooterWheel.shooterWheelSpeedControllerAft.getError());
SmartDashboard.putNumber("shooter acceptErr", shooterWheel.shooterWheelSpeedControllerAft.getAcceptErrorDiff());



//TODO Auto-generated method stub
//put Arm data on screen
SmartDashboard.putNumber("armEncoder", arm.armPosController.getSensorRate());
SmartDashboard.putNumber("arm PID Output", arm.armPosController.getControlOutput());
SmartDashboard.putBoolean("ArmAtPos", arm.armPosController.isFinished());
SmartDashboard.putBoolean("enable", arm.armPosController.isEnabled());
SmartDashboard.putNumber("executionTime", arm.armPosController.getExecutionTime());
SmartDashboard.putNumber("armSetPoint", arm.armPosController.getSetPoint());



SmartDashboard.putNumber("arm Err", arm.armPosController.getError());
SmartDashboard.putNumber("arm acceptErr", arm.armPosController.getAcceptErrorDiff());



//drive shooter wheel
//try
//{
//
//
////hood.shooterWheelController.setSp(SmartDashboard.getDouble("shooterSetPoint"));
////System.out.println(SmartDashboard.getString("compTime"));
//
////get shooter gains from dashboard
//hood.shooterWheelController.setpGain(SmartDashboard.putNumber("P"));
//hood.shooterWheelController.setiGain(SmartDashboard.putNumber("I"));
//hood.shooterWheelController.setdGain(SmartDashboard.putNumber("D"));
//
//} catch (NetworkTableKeyNotDefined e)
//{
//// TODO Auto-generated catch block
//e.printStackTrace();
//}


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