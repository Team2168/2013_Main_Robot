package frc2168_2013.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.commands.Presets.Preset_FrontOfPyramid_3pt;
import frc2168_2013.commands.subSystems.Hopper.ShootSingleDisc;
import frc2168_2013.commands.subSystems.ShooterWheel.PID_ShooterPause;

/**
 * Auto command. Sits still and shoots discs from close range at the three point goal.
 * 
 * @author James
 *
 */
public class FrontOfPyramid_3pt_Left extends CommandGroup {
	public FrontOfPyramid_3pt_Left() {
		//get arm and shooter ready
		addParallel(new Preset_FrontOfPyramid_3pt());
		
		//Shoot two discs
		addSequential(new ShootSingleDisc());
		addSequential(new ShootSingleDisc());
		
		//stop wheel
		addSequential(new PID_ShooterPause());
		//lower arm

		
		//Drive to the mid-field line
		//  drive backward
		//addSequential(new DriveDrivetrainStraight(-XX));
		//  rotate to square up with the midfield line
		//addSequential(new DriveDrivetrainTurn(-45));
		//  drive backward
		//addSequential(new DriveDrivetrainStraight(-XX));
	}
}
