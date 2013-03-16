package frc2168_2013.commands.Presets;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.RobotMap;
import frc2168_2013.commands.subSystems.ShooterAngle.ShooterAngleStow;
import frc2168_2013.commands.subSystems.ShooterWheel.PID_SetAftWheelSpeed;
import frc2168_2013.commands.subSystems.ShooterWheel.PID_SetFwdWheelSpeed;

/**
 * Shots from against the pyramid, closest to the goal
 */
public class Preset_FrontOfPyramid_3pt extends CommandGroup {
	
	public Preset_FrontOfPyramid_3pt(){
		//Raise arm
		addParallel(new ShooterAngleStow());
		//Get wheel up to speed
		addParallel(new PID_SetAftWheelSpeed(RobotMap.FRONT_PYRAMID_3PT_SPEED));
		addParallel(new PID_SetFwdWheelSpeed(RobotMap.FRONT_PYRAMID_3PT_SPEED));
	}
}
