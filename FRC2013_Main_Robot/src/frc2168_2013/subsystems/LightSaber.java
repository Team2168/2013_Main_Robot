package frc2168_2013.subsystems;


import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.RobotMap;


public class LightSaber extends Subsystem {
	Relay actuator;
	
	public LightSaber() {
		actuator = new Relay(RobotMap.lightSaber);
	}
	
	/**
	 * Tells the hanger what to do when it starts
	 */
	
	protected void initDefaultCommand() {

	}
	
	public void Extend(){
		//TODO: Verify that kForward disengages the hanger
		actuator.set(Relay.Value.kForward);
	}
    
    /**
     * Lower/deploy the intake mechanism.
     */
	public void Stow() {
		//TODO: Verify that kForward engages the hanger
		actuator.set(Relay.Value.kReverse);
	}
	
	public boolean isStowed(){
		//TODO: Verify that kForward disengages the hanger
		return actuator.get() == Relay.Value.kReverse;
	}
	
	public boolean isExtended(){
		//TODO: Verify that kForward disengages the hanger
		return actuator.get() == Relay.Value.kForward;
	}
}
