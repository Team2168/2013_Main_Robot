package frc2168_2013.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.commands.*;
import frc2168_2013.commands.Presets.Preset_FrontOfPyramid_3pt;
import frc2168_2013.commands.subSystems.Arm.DriveArmHome;
import frc2168_2013.commands.subSystems.Shooter.ShooterPIDPause;

/**
 * Auto command. Sits still and shoots discs from close range at the three point goal.
 * 
 * @author James
 *
 */
public class FrontOfPyramid_3pt_Center extends CommandGroup {
	public FrontOfPyramid_3pt_Center() {
		//Get arm and shooter ready
		addSequential(new Preset_FrontOfPyramid_3pt());
		
		//Shoot two discs
		addSequential(new ShootSingleFrisbee());
		addSequential(new ShootSingleFrisbee());
		
		//stop wheel
		addSequential(new ShooterPIDPause());
		//lower arm
		addSequential(new DriveArmHome());
		
		//Drive around the tower to the mid-field line
		//  drive forward
		//addSequential(new DriveDrivetrainStraight(XX));
		//  rotate 90
		//addSequential(new DriveDrivetrainTurn(90));
		//  drive forward
		//addSequential(new DriveDrivetrainStraight(XX));
		//  rotate -90
		//addSequential(new DriveDrivetrainTurn(-90));
		//  drive backward to mid-field line
		//addSequential(new DriveDrivetrainStraight(-XX));
	}
}
