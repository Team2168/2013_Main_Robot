package frc2168_2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.RobotMap;

/**
 * Shots into the goal from against the wall (right under the goal).
 */
public class Preset_Pyramid_5pt extends CommandGroup {
	
	public Preset_Pyramid_5pt(){
		//Raise arm
		addParallel(new RaiseArmSequence(RobotMap.PYRAMID_5PT_ANGLE));
		//Get wheel up to speed
		addParallel(new ShooterPIDSpeed(RobotMap.PYRAMID_5PT_SPEED));
	}
}
