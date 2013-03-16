package frc2168_2013.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.RobotMap;
import frc2168_2013.commands.Sleep;
import frc2168_2013.commands.subSystems.DriveTrain.DriveDrivetrainStraight;
import frc2168_2013.commands.subSystems.Hopper.ShootSingleDisc;
import frc2168_2013.commands.subSystems.ShooterAngle.ShooterAngleExtend;
import frc2168_2013.commands.subSystems.ShooterWheel.PID_ShooterPause;
import frc2168_2013.commands.subSystems.ShooterWheel.PID_SetAftWheelSpeed;

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
		addParallel(new ShooterAngleExtend());
		addParallel(new PID_SetAftWheelSpeed(RobotMap.BACK_PYRAMID_3PT_SPEED));
		addSequential(new Sleep(), 6.5 );
		
		//drive forward 3ft - 11inches
		addSequential(new DriveDrivetrainStraight(((3*12)-8-7.5)));
		
		//Shoot three discs
		addSequential(new ShootSingleDisc());
		addSequential(new Sleep(),0.5 );
		addSequential(new ShootSingleDisc());
		addSequential(new Sleep(),0.5 );
		addSequential(new ShootSingleDisc());
		
		//addSequential(new ShootSingleFrisbee());
		//                   //stop wheel
		addSequential(new PID_ShooterPause());
		

		
		


		
		//Drive to the mid-field line
		//  drive backward

	}
}
