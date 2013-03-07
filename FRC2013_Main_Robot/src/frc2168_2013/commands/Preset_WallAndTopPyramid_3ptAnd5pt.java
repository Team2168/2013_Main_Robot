package frc2168_2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.RobotMap;

/**
 * shoot into 3pt from beneath the wall and 5pt from beneath the pyramid
 */
public class Preset_WallAndTopPyramid_3ptAnd5pt extends CommandGroup {
	
	public Preset_WallAndTopPyramid_3ptAnd5pt(){
		//Raise arm
		addParallel(new WallAndTopPyramidPosition());
		//Get wheel up to speed
		addParallel(new ShooterPIDSpeed(RobotMap.WALL_3PT_SPEED));
	}
}
