package frc2168_2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.RobotMap;

/**
 * shoot into 5pt from beneath the pyramid
 */
public class Preset_TopPyramid_5pt extends CommandGroup {
	
	public Preset_TopPyramid_5pt(){
		//Raise arm
		addParallel(new WallAndTopPyramidPosition());
		//Get wheel up to speed
		addParallel(new ShooterPIDSpeed(RobotMap.PYRAMID_5PT_SPEED));
	}
}
