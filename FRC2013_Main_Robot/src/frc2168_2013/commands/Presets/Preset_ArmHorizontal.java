package frc2168_2013.commands.Presets;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.RobotMap;
import frc2168_2013.commands.RaiseArmSequence;

/**
 * Shots from against the pyramid, closest to the goal
 */
public class Preset_ArmHorizontal extends CommandGroup {
	
	public Preset_ArmHorizontal(){
		//Raise arm
		addSequential(new RaiseArmSequence(RobotMap.ARM_HORIZONTAL_ANGLE));
	}
}
