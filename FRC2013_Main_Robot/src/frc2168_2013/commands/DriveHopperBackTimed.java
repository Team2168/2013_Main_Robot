package frc2168_2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveHopperBackTimed extends CommandGroup {
	
	public DriveHopperBackTimed(){
		addSequential(new DriveHopperJoystick(-0.4), 0.4);
	}

}
