package frc2168_2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.RobotMap;

public class Preset_FrontOfPyramid_3pt extends CommandGroup {
	
	public Preset_FrontOfPyramid_3pt(){
		//pre-spin shooter wheel
		addParallel(new ShooterPIDSpeed(RobotMap.PRE_SPIN_SPEED));
		//Raise arm
		addSequential(new RaiseArmSequence(RobotMap.FRONT_PYRAMID_3PT_ANGLE));
		//Get shooter to speed
		addSequential(new ShooterPIDSpeed(RobotMap.FRONT_PYRAMID_3PT_SPEED));
		//Wait until it is up to speed
//		while(!CommandBase.shooter.atSpeed()){
//			addSequential(new Sleep(), 0.1);
//		}
	}
}
