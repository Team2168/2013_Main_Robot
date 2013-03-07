package frc2168_2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.RobotMap;

/**
 * shoot into 3pt from beneath the goal
 */
public class Preset_Wall_3pt extends CommandGroup {
	
	public Preset_Wall_3pt(){
		//Raise arm
		addParallel(new WallAndTopPyramidPosition());
		//Get wheel up to speed
		addParallel(new ShooterPIDSpeed(RobotMap.WALL_3PT_SPEED));
	}
}
