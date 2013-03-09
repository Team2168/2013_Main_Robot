package frc2168_2013.commands.subSystems.Hopper;

import edu.wpi.first.wpilibj.command.*;
import frc2168_2013.commands.CommandBase;
import frc2168_2013.subsystems.Hopper;


public class DriveHopperBackTimed extends CommandGroup {
	
	public DriveHopperBackTimed(){
		if(CommandBase.hopper.getNumberOfDiscs()<4)
			addSequential(new DriveHopperJoystick(-0.2), 0.1);
	}

}
