package frc2168_2013.commands.Presets;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.RobotMap;
import frc2168_2013.commands.RaiseArmSequence;
import frc2168_2013.commands.subSystems.Shooter.ShooterPIDSpeed;

/**
 * Shots from against the pyramid, closest to the goal
 */
public class Preset_FrontOfPyramid_3pt extends CommandGroup {
	
	public Preset_FrontOfPyramid_3pt(){
		//Raise arm
		addParallel(new RaiseArmSequence(RobotMap.FRONT_PYRAMID_3PT_ANGLE));
		//Get wheel up to speed
		addParallel(new ShooterPIDSpeed(RobotMap.FRONT_PYRAMID_3PT_SPEED));
	}
}
