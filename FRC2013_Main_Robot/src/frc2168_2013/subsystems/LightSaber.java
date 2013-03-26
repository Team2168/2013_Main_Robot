package frc2168_2013.subsystems;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.RobotMap;


public class LightSaber extends Subsystem {
	DoubleSolenoid actuator;
	
	public LightSaber() {
		actuator = new DoubleSolenoid(RobotMap.lightSaberRaise,
				RobotMap.lightSaberLower);
		//start with the light sabers lowered
		stow();
	}
	
	/**
	 * Tells the hanger what to do when it starts
	 */
	
	protected void initDefaultCommand() {

	}
	
	public void extend(){
		//TODO: Verify that kForward disengages the hanger
		actuator.set(DoubleSolenoid.Value.kForward);
	}
    
	public void stow() {
		//TODO: Verify that kForward engages the hanger
		actuator.set(DoubleSolenoid.Value.kReverse);
	}
}
