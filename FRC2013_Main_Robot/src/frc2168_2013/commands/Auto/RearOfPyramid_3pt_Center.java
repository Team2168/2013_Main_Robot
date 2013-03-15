package frc2168_2013.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.RobotMap;
import frc2168_2013.commands.*;
import frc2168_2013.commands.Presets.Preset_FrontOfPyramid_3pt;
import frc2168_2013.commands.Presets.Preset_RearOfPyramid_3pt;
import frc2168_2013.commands.subSystems.Arm.DriveArmHome;
import frc2168_2013.commands.subSystems.ArmPnumatic.ArmPnuematicExtend;
import frc2168_2013.commands.subSystems.ArmPnumatic.ArmPnuematicStow;
import frc2168_2013.commands.subSystems.DriveTrain.DriveDrivetrainStraight;
import frc2168_2013.commands.subSystems.Hopper.DriveHopperJoystick;
import frc2168_2013.commands.subSystems.Shooter.ShooterPIDPause;
import frc2168_2013.commands.subSystems.Shooter.ShooterPIDSpeed;
import frc2168_2013.commands.subSystems.Stopper.StopperDisengage;

/**
 * Auto command. Sits still and shoots discs from close range at the three point goal.
 * 
 * @author James
 *
 */
public class RearOfPyramid_3pt_Center extends CommandGroup {
	public RearOfPyramid_3pt_Center() {
		//Drive back 3 ft
		addSequential(new DriveDrivetrainStraight(-((3*12)-8)));
		//raise arm
		addParallel(new ArmPnuematicExtend());
		addParallel(new ShooterPIDSpeed(RobotMap.BACK_PYRAMID_3PT_SPEED));
		addSequential(new Sleep(), 6.5 );
		
		//drive forward 3ft - 11inches
		addSequential(new DriveDrivetrainStraight(((3*12)-8-7.5)));
		
		//Shoot three discs
		addSequential(new ShootSingleFrisbee());
		addSequential(new Sleep(),0.5 );
		addSequential(new ShootSingleFrisbee());
		addSequential(new Sleep(),0.5 );
		addSequential(new StopperDisengage());
		addSequential(new DriveHopperJoystick(RobotMap.hopperVoltage), 2.5);
		//addSequential(new ShootSingleFrisbee());
		//                   //stop wheel
		addSequential(new ShooterPIDPause());
		

		
		


		
		//Drive to the mid-field line
		//  drive backward

	}
}