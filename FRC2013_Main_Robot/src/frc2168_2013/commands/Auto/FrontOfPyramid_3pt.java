package frc2168_2013.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.commands.*;

/**
 * Auto command. Sits still and shoots discs from close range at the three point goal.
 * 
 * @author James
 *
 */
public class FrontOfPyramid_3pt extends CommandGroup {
	public FrontOfPyramid_3pt() {
		//get arm and shooter ready
		addSequential(new Preset_AutoAndFrontPyramid_3pt());
		
		//Shoot two discs
		addSequential(new ShootSingleFrisbee());
		addSequential(new ShootSingleFrisbee());
		
		//stop wheel
		addSequential(new ShooterPIDPause());
		//lower arm
		addSequential(new Preset_Arm_Load());
		
		//Drive to the mid-field line
		//  drive backward
		//addSequential(new DriveDrivetrainStraight(-XX));
		//  rotate to square up with the midfield line
		//addSequential(new DriveDrivetrainTurn(-45));
		//  drive backward
		//addSequential(new DriveDrivetrainStraight(-XX));
	}
}
