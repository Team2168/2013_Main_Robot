package frc2168_2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.RobotMap;

public class Preset_RearOfPyramid_3pt extends CommandGroup {
	
	public Preset_RearOfPyramid_3pt(){
		//stop shooter wheel
		addParallel(new ShooterPIDPause());
		//Raise arm
		addSequential(new RaiseArmSequence(RobotMap.WALL_3PT_ANGLE));
		//Get shooter to speed
		addSequential(new ShooterPIDSpeed(RobotMap.WALL_3PT_SPEED));
		//Wait until it is up to speed
//		while(!CommandBase.shooter.atSpeed()){
//			addSequential(new Sleep(), 0.1);
//		}
	}
}
