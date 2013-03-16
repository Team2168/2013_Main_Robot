package frc2168_2013.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.commands.*;
import frc2168_2013.commands.Presets.Preset_FrontOfPyramid_3pt;
import frc2168_2013.commands.subSystems.Arm.DriveArmHome;
import frc2168_2013.commands.subSystems.Hopper.HopperExtend;
import frc2168_2013.commands.subSystems.Hopper.HopperStow;
import frc2168_2013.commands.subSystems.Hopper.ShootSingleDisc;
import frc2168_2013.commands.subSystems.ShooterWheel.PID_ShooterPause;

/**
 * Auto command. Sits still and shoots discs from close range at the three point goal.
 * 
 * @author James
 *
 */
public class FrontOfPyramid_3pt_Center extends CommandGroup {
	public FrontOfPyramid_3pt_Center() {
		//Get arm and shooter ready
		addParallel(new Preset_FrontOfPyramid_3pt());
		
		//Shoot two discs
		addSequential(new ShootSingleDisc());
		addSequential(new ShootSingleDisc());
		
		//stop wheel
		addSequential(new PID_ShooterPause());
		
		//lower arm
		addSequential(new DriveArmHome());
		
	}
}
