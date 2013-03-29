package frc2168_2013.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.RobotMap;
import frc2168_2013.commands.*;
import frc2168_2013.commands.Presets.Preset_FrontOfPyramid_3pt;
import frc2168_2013.commands.Presets.Preset_RearOfPyramid_3pt;
import frc2168_2013.commands.subSystems.DriveTrain.DriveDrivetrainStraight;
import frc2168_2013.commands.subSystems.Hopper.ShootSingleDisc;
import frc2168_2013.commands.subSystems.ShooterAngle.ShooterAngleExtend;
import frc2168_2013.commands.subSystems.ShooterAngle.ShooterAngleStow;
import frc2168_2013.commands.subSystems.ShooterWheel.DriveShooterWithConstant;
import frc2168_2013.commands.subSystems.ShooterWheel.PID_ShooterPause;
import frc2168_2013.commands.subSystems.ShooterWheel.PID_SetAftWheelSpeed;

/**
 * Auto command. Sits still and shoots discs from close range at the three point goal.
 * 
 * @author James
 *
 */
public class RearOfPyramid_3pt_Side extends CommandGroup {
	
	private double firstDiscTime;
	private double secondDiscTime;
	private double thirdDiscTime;
	
	//shoots three discs in auto, timed apart
	public RearOfPyramid_3pt_Side(double firstDiscTime, double secondDiscTime, double thirdDiscTime) {
		
		this.firstDiscTime = firstDiscTime;
		this.secondDiscTime = secondDiscTime;
		this.thirdDiscTime = thirdDiscTime;
		
		
		//set shooter angle to stow position
		addParallel(new ShooterAngleStow());
				
//		addParallel(new PID_SetAftWheelSpeed(RobotMap.AFT_SHOOTERWHEEL_BACK_PYRAMID_3PT_SPEED));
//		addParallel(new PID_SetFwdWheelSpeed(RobotMap.FWD_SHOOTERWHEEL_BACK_PYRAMID_3PT_SPEED));
//		//set shooterwheel speeds
		
		//driver both shooterwheels at full speed
		addParallel(new DriveShooterWithConstant(1, 1));
		
		
		//Shoot three discs
		addSequential(new Sleep(), firstDiscTime);
		addSequential(new ShootSingleDisc());
		addSequential(new Sleep(), secondDiscTime);
		addSequential(new ShootSingleDisc());
		addSequential(new Sleep(), thirdDiscTime);
		//wait longer for the last disk to drop into place before shooting
		addSequential(new ShootSingleDisc());
		
		addSequential(new Sleep(), secondDiscTime);
		addSequential(new ShootSingleDisc());
		addSequential(new Sleep(), thirdDiscTime);
		//wait longer for the last disk to drop into place before shooting
		addSequential(new ShootSingleDisc());
		
		addSequential(new Sleep(), secondDiscTime);
		addSequential(new ShootSingleDisc());
		addSequential(new Sleep(), thirdDiscTime);
		//wait longer for the last disk to drop into place before shooting
		addSequential(new ShootSingleDisc());
	
		//drive backward                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           addSequential(new Sleep(),1 );
		//addSequential(new DriveDrivetrainStraight(-((8*12)-8)));

	}
}
