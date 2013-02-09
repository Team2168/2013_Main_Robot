package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hanger extends Subsystem {
	Talon hangerMotor;
	//Declares the motor 
	
	double engageSpeed = 0.5;
	double disengageSpeed = -0.5;
	/**
	 * Values for the motor when initialized/engaged/disengaged 
	 */
	
	/**
	 * Tells the hanger what to do when it starts
	 */
	
	protected void initDefaultCommand() {
		//setDefaultCommand(new DisengageHanger());
	}
	
	/**
	 * Sets the speed for the motor when engaging. 
	 */
	public void engage(){
		hangerMotor.set(engageSpeed);
	}
	
	/**
	 * Sets the speed for the motor when disengaging.
	 */
	public void disengage(){
		hangerMotor.set(disengageSpeed);
	}
	
	/**
	 * Stops the motor.
	 */
	public void stop(){
		hangerMotor.set(0.0);
	
	}
}
