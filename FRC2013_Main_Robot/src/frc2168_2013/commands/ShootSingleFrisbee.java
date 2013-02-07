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
	
	
	
	//get frisbee ready
	addSequential(new DriveUntilFrisbee());

	
	//wait for some length of time	
	addSequential(new Sleep(), .200); //wait for 1/5 second;

	
	//verify shooter is at speed
	//addSequential(new PID_ShooterAtSpeed());

	
	//shoot ball
	addSequential(new DriveUntilNoFrisbee());

	
	//get another ball or timeout
	addSequential(new DriveUntilFrisbee(),2);

	

	//shooter wheel will stop if timed out
	addSequential(new StopShooterWheel());
	
	}
}