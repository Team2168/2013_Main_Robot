package frc2168_2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.RobotMap;
import frc2168_2013.commands.subSystems.Hanger.DriveUntilFrisbee;
import frc2168_2013.commands.subSystems.Hanger.DriveUntilNoFrisbee;
import frc2168_2013.commands.subSystems.Hopper.DriveHopperBackTimed;
import frc2168_2013.commands.subSystems.Stopper.StopperDisengage;
import frc2168_2013.commands.subSystems.Stopper.StopperEngage;

/**
 * 
 * @author Shriji
 *
 */
public class ShootSingleFrisbee extends CommandGroup
{
	public ShootSingleFrisbee(){
		addSequential(new StopperEngage());
		
		//get frisbee ready
		addSequential(new DriveUntilFrisbee(), 2.0);
		
		//unload the stopper
		addSequential(new DriveHopperBackTimed());
		
		//TODO: Determine if this wait is necessary
		//wait so there is a consistent entry velocity into the shooter wheel
		addSequential(new Sleep(), .200); //wait;
	
		//verify shooter is at speed
		//addSequential(new PID_ShooterAtSpeed());
		//addSequential(new PID_ArmAtPosition());
		
		//disengage the hard stop
		addSequential(new StopperDisengage());
	
		//shoot disc
		addSequential(new DriveUntilNoFrisbee());
		
		addSequential(new Sleep(), 0.1);
		addSequential(new StopperEngage());
		
		//get another disc ready to fire or timeout
		addSequential(new DriveUntilFrisbee(), 2);
		addSequential(new DriveHopperBackTimed());
	}
}