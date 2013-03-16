package frc2168_2013.commands.Presets;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.RobotMap;
import frc2168_2013.commands.subSystems.ShooterWheel.PID_SetAftWheelSpeed;

/**
 * Shots into the goal from against the wall (right under the goal).
 */
public class Preset_Wall_3pt extends CommandGroup {
	
	public Preset_Wall_3pt(){
		//Raise arm
		//addParallel();
		//Get wheel up to speed
		addParallel(new PID_SetAftWheelSpeed(RobotMap.WALL_3PT_SPEED));
	}
}
