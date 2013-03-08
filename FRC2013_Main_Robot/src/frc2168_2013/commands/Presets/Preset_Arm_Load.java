package frc2168_2013.commands.Presets;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.RobotMap;
import frc2168_2013.commands.subSystems.Arm.DriveArmHome;
import frc2168_2013.commands.subSystems.Shooter.ShooterPIDPause;

public class Preset_Arm_Load extends CommandGroup {
	
	public Preset_Arm_Load(){
		//pre-spin shooter wheel
		addSequential(new ShooterPIDPause());
		//Drive arm to stow
		addSequential(new DriveArmHome());
	}
}
