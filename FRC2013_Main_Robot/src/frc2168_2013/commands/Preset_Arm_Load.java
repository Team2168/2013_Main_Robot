package frc2168_2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.RobotMap;

public class Preset_Arm_Load extends CommandGroup {

	public Preset_Arm_Load(){
		//pre-spin shooter wheel
		addSequential(new ShooterPIDPause());
		//Drive arm to stow
		addSequential(new StowArmPosition());
	}
}