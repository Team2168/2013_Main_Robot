package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.commands.SetIntakeSpeedPWM;

public class Intake extends Subsystem {		

	/**
	 * Set the default command
	 */
	public void initDefaultCommand() {
		//Set the default command to stop the rollers
		setDefaultCommand(new SetIntakeSpeedPWM(0.0, 0.0));
		
		//TODO: Should probably also stow the intake.
    }
    
	/**
	 * Set the speed of the intake rollers in PWM units (1.0 to -1.0).
	 * 
	 * @param upperRollerSpeed the PWM speed for the upper roller
	 * @param LowerRollerSpeed the PWM speed for the lower roller
	 */
	public void setPWM(double upperRollerSpeed, double LowerRollerSpeed) {
		//TODO: write the code for this method
	}
	
	/**
	 * Set the speed of the intake rollers in RPM
	 * 
	 * @param upperRollerSpeed the speed of the upper roller (RPM)
	 * @param LowerRollerSpeed the speed of the lower roller (RPM)
	 */
    public void setSpeed(double upperRollerSpeed, double LowerRollerSpeed) {
		//TODO: write the code for this method    	
    }
    
    /**
     * Raise/stow the intake mechanism.
     */
    public void raise(){
		//TODO: write the code for this method
    }
    
    /**
     * Lower/deploy the intake mechanism.
     */
    public void lower(){
		//TODO: write the code for this method
    }
}