package frc2168_2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RaiseArmSequence extends CommandGroup {
	
	public RaiseArmSequence(double angle)
	{
		addSequential(new DriveArmWithConstant());
		addSequential(new Sleep(),1);
		addSequential(new ArmPIDPosition(angle));
		
	}

}
