package frc2168_2013.commands.Presets;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.commands.subSystems.Intake.IntakeHopperPosition;
import frc2168_2013.commands.subSystems.Intake.IntakeStowPosition;
import frc2168_2013.commands.subSystems.ShooterAngle.ShooterAngleExtend;
/**
 * Shots from against the pyramid, further from the goal
 */
public class Preset_PreLoadPosition extends CommandGroup {
	
	public Preset_PreLoadPosition(){

		addSequential(new IntakeHopperPosition());

		//lower shooter angle to stow position
		addSequential(new ShooterAngleExtend());

		
	}
}
