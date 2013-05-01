package frc2168_2013.commands.Presets;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.commands.subSystems.ShooterAngle.ShooterAngleStow;
import frc2168_2013.commands.subSystems.ShooterWheel.DriveShooterWithConstant;

/**
 * Shots from against the pyramid, further from the goal
 */
public class Preset_RearOfPyramid_3pt extends CommandGroup {
	
	public Preset_RearOfPyramid_3pt(){
		//To line up for this shot, the operator needs to place the arm at the
		// horizontal position, then drive into the pyramid and raise the arm
		// to its final angle.
		//Instead of sequencing the arm through different positions in this
		// command. He should use the manual control or order the arm to the
		// half court? position. One of them will hopefully be low enough to
		// get through the pyramid. We're out of buttons...
		
		//turn shooter wheels on
		addParallel(new DriveShooterWithConstant(1, 1));
		
		//lower shooter angle to stow position
		addSequential(new ShooterAngleStow());
		
		
//		//Get wheel up to speed
//		addParallel(new PID_SetAftWheelSpeed(RobotMap.BACK_PYRAMID_3PT_SPEED));
//		addParallel(new PID_SetFwdWheelSpeed(RobotMap.BACK_PYRAMID_3PT_SPEED));
		
		
	}
}
