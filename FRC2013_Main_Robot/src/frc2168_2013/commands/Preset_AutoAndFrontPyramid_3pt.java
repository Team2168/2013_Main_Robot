package frc2168_2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.RobotMap;

/**
 * Shots into the goal from against the wall (right under the goal).
 */
public class Preset_AutoAndFrontPyramid_3pt extends CommandGroup {
	
	public Preset_AutoAndFrontPyramid_3pt(){
		//Raise arm
		addParallel(new AutoAndFrontPyramidPosition());
		//Get wheel up to speed
		addParallel(new ShooterPIDSpeed(RobotMap.PYRAMID_5PT_SPEED));
	}
}
