package frc2168_2013.commands.subSystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc2168_2013.commands.CommandBase;

public class PID_ShooterAtSpeed extends CommandBase
{
public PID_ShooterAtSpeed()
{
	//no need to require the subsystem
	//the PID controller owns the subsystem
}

protected void initialize()
{
// TODO Auto-generated method stub


}

protected void execute()
{
// TODO Auto-generated method stub

}

protected boolean isFinished()
{
// TODO Auto-generated method stub
return shooterWheel.shooterWheelSpeedController.isFinished();
}

protected void end()
{
// TODO Auto-generated method stub

}

protected void interrupted()
{
// TODO Auto-generated method stub
end();
}

}