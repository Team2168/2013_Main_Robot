package frc2168_2013.commands;

import edu.wpi.first.wpilibj.command.Command;

public class PID_ArmAtPosition extends CommandBase
{
public PID_ArmAtPosition()
{

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
return arm.armPosController.isFinished();
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