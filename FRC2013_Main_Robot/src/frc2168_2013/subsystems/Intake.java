package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.RobotMap;

public class Intake extends Subsystem {		
	
	Relay actuatorS; //short actuator
	Relay actuatorL; //long actuator
	
	public Intake() {
		//TODO: If an intake is added, flesh this stuff out.
		actuatorS = new Relay(RobotMap.intakeS);
		actuatorL = new Relay(RobotMap.intakeL);
	}

	/**
	 * Set the default command
	 */
	
	public void initDefaultCommand() {
		
		//TODO: Should probably also stow the intake.
    }
    
	
    /**
     * Lower the intake mechanism.
     */
	public void StowToHopper(){
		//TODO: Verify that kForward disengages the hanger
		actuatorS.set(Relay.Value.kReverse);
		actuatorL.set(Relay.Value.kForward);
	}
    
    /**
     * Raise the intake mechanism.
     */
	public void HopperToLoad() {
		//TODO: Verify that kForward engages the hanger
		actuatorS.set(Relay.Value.kReverse);
		actuatorL.set(Relay.Value.kReverse);
	}
	
	public void LoadToHopper(){
		actuatorS.set(Relay.Value.kReverse);
		actuatorL.set(Relay.Value.kForward);		
	}
	
	public void HopperToStow(){
		actuatorS.set(Relay.Value.kForward);
		actuatorL.set(Relay.Value.kForward);
		
	}
}
