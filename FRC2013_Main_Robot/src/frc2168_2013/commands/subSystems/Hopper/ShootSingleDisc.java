package frc2168_2013.commands.subSystems.Hopper;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.CommandBaseRobot;
import frc2168_2013.RobotMap;
import frc2168_2013.commands.*;
import frc2168_2013.commands.Presets.Preset_FrontOfPyramid_3pt;
import frc2168_2013.commands.Presets.Preset_RearOfPyramid_3pt;
import frc2168_2013.commands.subSystems.DriveTrain.DriveDrivetrainStraight;
import frc2168_2013.commands.subSystems.ShooterAngle.ShooterAngleExtend;
import frc2168_2013.commands.subSystems.ShooterAngle.ShooterAngleStow;
import frc2168_2013.commands.subSystems.ShooterWheel.OL_ShooterAtSpeed;
import frc2168_2013.commands.subSystems.ShooterWheel.PID_ShooterPause;
import frc2168_2013.commands.subSystems.ShooterWheel.PID_SetAftWheelSpeed;

/**
 * Auto command. Sits still and shoots discs from close range at the three point goal.
 * 
 * @author James
 *
 */
public class ShootSingleDisc extends CommandGroup {
	public ShootSingleDisc() {
		//addSequential(new OL_ShooterAtSpeed(RobotMap.AFT_SHOOTERWHEEL_BACK_PYRAMID_3PT_SPEED,RobotMap.FWD_SHOOTERWHEEL_BACK_PYRAMID_3PT_SPEED));

		addSequential(new HopperReload());
		addSequential(new Sleep(),0.15);
		addSequential(new HopperFire());
		addSequential(new Sleep(),0.15);
		addSequential(new HopperReload());

		
		
	}
}
