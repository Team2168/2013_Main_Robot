package frc2168_2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.RobotMap;

/**
 * Shots from half court
 */
public class Preset_HalfCourt_3pt extends CommandGroup {
	
	public Preset_HalfCourt_3pt(){
		//Raise arm
		addParallel(new RaiseArmSequence(RobotMap.HALF_COURT_3PT_ANGLE));
		//Get wheel up to speed
		addParallel(new ShooterPIDSpeed(RobotMap.HALF_COURT_3PT_SPEED));
	}
}
