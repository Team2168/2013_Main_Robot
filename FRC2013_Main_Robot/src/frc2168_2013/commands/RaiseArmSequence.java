package frc2168_2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.commands.subSystems.Arm.ArmPIDPosition;
import frc2168_2013.commands.subSystems.Arm.DriveArmWithConstant;

public class RaiseArmSequence extends CommandGroup {
	
	public RaiseArmSequence(double angle)
	{
		addSequential(new DriveArmWithConstant());
		addSequential(new Sleep(),1);
		addSequential(new ArmPIDPosition(angle));
		
	}

}
