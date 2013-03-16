package frc2168_2013.commands.Presets;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.RobotMap;
import frc2168_2013.commands.RaiseArmSequence;
import frc2168_2013.commands.subSystems.ShooterWheel.PID_SetAftWheelSpeed;

/**
 * Shots into the goal from against the wall (right under the goal).
 */
public class Preset_Pyramid_5pt extends CommandGroup {
	
	public Preset_Pyramid_5pt(){
		//Raise arm
		addParallel(new RaiseArmSequence(RobotMap.PYRAMID_5PT_ANGLE));
		//Get wheel up to speed
		addParallel(new PID_SetAftWheelSpeed(RobotMap.PYRAMID_5PT_SPEED));
	}
}
