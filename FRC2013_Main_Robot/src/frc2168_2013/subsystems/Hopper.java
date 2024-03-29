package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.RobotMap;


public class Hopper extends Subsystem {

	DoubleSolenoid actuator;
	Relay teamDiscLight;
	
	public Hopper() {
		teamDiscLight = new Relay(RobotMap.teamDiscLight);
		
		actuator = new DoubleSolenoid(RobotMap.hopperReload,
                RobotMap.hopperFire);
		
		//Start with actuator ready to fire a disc (reloaded)
		reloadDisc();
	}
	
	/**
	 * Bring a disc into the shooter wheels
	 */
	public void fireDisc() {
		actuator.set(DoubleSolenoid.Value.kReverse);
	}
	 
	/**
	 * Reload the shooter piston in preparation for firing.
	 */
	public void reloadDisc() {
		actuator.set(DoubleSolenoid.Value.kForward);
	}
	
	protected void initDefaultCommand() {
		//TODO: Add default command.

	}
	 
	 /**
	  * Turn on the team disc light indicator.
	  */
	 public void setDiscLightOn() {
		 //Apply 12V to the + spike output, and gnd to the - spike output
		 teamDiscLight.set(Relay.Value.kForward);
	 }
	 
	 /**
	  * Turn off the team disc light indicator.
	  */
	 public void setDiscLightOff() {
		 teamDiscLight.set(Relay.Value.kOff);
	 }
}
