package frc2168_2013.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.commands.*;

/**
 * Auto command. Sits still and shoots discs from close range at the three point goal.
 * 
 * @author James
 *
 */
public class FrontOfPyramid_3pt_Right extends CommandGroup {
	public FrontOfPyramid_3pt_Right() {
		//Get arm and shooter ready
		addSequential(new Preset_FrontOfPyramid_3pt());
		
		//Shoot two discs
		addSequential(new ShootSingleFrisbee());
		addSequential(new ShootSingleFrisbee());
		
		//stop wheel
		addSequential(new ShooterPIDPause());
		//lower arm
		addSequential(new DriveArmHome());
		
		//Drive to the mid-field line
		//  drive backward
		//addSequential(new DriveDrivetrainStraight(-XX));
		//  rotate to square up with the midfield line
		//addSequential(new DriveDrivetrainTurn(45));
		//  drive backward
		//addSequential(new DriveDrivetrainStraight(-XX));
	}
}
