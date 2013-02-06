package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.commands.DisengageHanger;


public class Hanger extends Subsystem {
	DoubleSolenoid actuater;
	//Declares the solenoid 
	
	double actuaterAngle;
	
	Value engaged;
	Value disengaged;
	//values for the solenoid when engaged/disengaged
	
	protected void initDefaultCommand() {
		setDefaultCommand(new DisengageHanger());
	}
	
	public void engage(){
		actuater.set(engaged);
		
	//Sets da value for da solenoid when engaged
	}
	
	public void disengage(){
		actuater.set(disengaged);
		
	//Sets da value for da solenoid  when not engaged
	}
	
	public double getAngle(double actuaterAngle){
		return (0.0);
		//Figure out what gets/sets the angle at the base of the hanger
		//Finish the code hear
	}
	
	public void raise(){
		//will raise the hanger to be able to go hang itself
	}
	
	public void lower(){
		//will lower the hanger back to the down position
	}
}