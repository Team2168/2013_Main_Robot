package frc2168_2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

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
		addSequential(new DriveUntilFrisbee());
	
		//TODO: Determine if this wait is necessary
		//wait so there is a consistent entry velocity into the shooter wheel
		addSequential(new Sleep(), .200); //wait;
	
		//verify shooter is at speed
		//addSequential(new PID_ShooterAtSpeed());
		
		//disengage the hard stop
		addSequential(new StopperDisengage());
	
		//shoot disc
		addSequential(new DriveUntilNoFrisbee());
	
		addSequential(new StopperEngage());
		
		//get another disc ready to fire or timeout
		addSequential(new DriveUntilFrisbee(), 2);
	
		
		//shooter wheel will stop if timed out
		//addSequential(new StopShooterWheel());
	}
}