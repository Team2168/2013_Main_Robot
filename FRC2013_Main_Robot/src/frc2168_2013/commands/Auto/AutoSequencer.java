package frc2168_2013.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.CommandBaseRobot;

/**
 * The auto command. Kicks off a number of different auto modes, depending on
 * what the operator has selected for an initial position and a destination
 * position on the dashboard.
 * 
 * @author James
 *
 */
public class AutoSequencer extends CommandGroup {
	
	public AutoSequencer() {
		//Sit still and shoot the discs we started with
		addSequential(new RearOfPyramid_3pt());
		
		System.out.println("Auto mode after shots:" + CommandBaseRobot.getAutoModeAfterShots());
		//Then drive to the selected destination position
		switch (CommandBaseRobot.getAutoModeAfterShots()) {
			case CommandBaseRobot.DEFEND_CENTER:
				addSequential(new DriveToFieldCenter());
				break;
			case CommandBaseRobot.TO_PROTECTED_LOADER:
				addSequential(new DriveToProtectedLoader());
				break;
			case CommandBaseRobot.TO_UNPROTECTED_LOADER:
				addSequential(new DriveToUnprotectedLoader());
				break;
			default:
				//Sit Still, do nothing
				break;
		}
	}
}
