package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import frc2168_2013.RobotMap;


public class Hopper extends Subsystem {

	DoubleSolenoid actuator;
	DigitalInput disc1, disc2, disc3, disc4;
	Relay teamDiscLight;
	
	public Hopper() {
		
		disc1 = new DigitalInput(RobotMap.hopperDisc1);
		disc2 = new DigitalInput(RobotMap.hopperDisc2);
		disc3 = new DigitalInput(RobotMap.hopperDisc3);
		disc4 = new DigitalInput(RobotMap.hopperDisc4);
		teamDiscLight = new Relay(RobotMap.teamDiscLight);
		
		actuator = new DoubleSolenoid(RobotMap.hopperExtend,
                RobotMap.hopperRetract);
	}
	
	
	protected void initDefaultCommand() {
		//TODO: Add default command.

	}


	/**
	 * Detects if disc1 is present. Disc1 is the disc closest to the shooter.
	 * 
	 * @return true if disc1 is present
	 */
	public boolean disc1Present(){
		//TODO: Verify true false for disc presence 
		return !disc1.get();
	}
	/**
	 * Detects if disc2 is present. Disc1 is the disc closest to the shooter.
	 * 
	 * @return true if disc2 is present
	 */
	public boolean disc2Present(){
		//TODO: Verify true false for disc presence 
		return !disc2.get();
	}
	/**
	 * Detects if disc3 is present. Disc1 is the disc closest to the shooter.
	 * 
	 * @return true if disc3 is present
	 */
	public boolean disc3Present(){
		//TODO: Verify true false for disc presence
		return !disc3.get();
	}
	/**
	 * Detects if disc4 is present. Disc1 is the disc closest to the shooter.
	 * 
	 * @return true if disc4 is present
	 */
	public boolean disc4Present(){
		//TODO: Verify true false for disc presence
		return !disc4.get();
	}
	
	/**
	 * Return the number of discs present in the hopper.
	 * 
	 * @return the number of discs
	 */
	 public int getNumberOfDiscs() {
	 	int i = 0;
	 	
    		if (disc1Present()) {
    			i++;
    		}
    		if (disc2Present()) {
    			i++;
    		}
    		if (disc3Present()) {
    			i++;
    		}
    		if (disc4Present()) {
    			i++;
    		}
    		
    		return i;
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
	 
		/**
		 * Engage the hanger / retract the actuators.
		 */
		public void Stow() {
			//TODO: Verify that kForward engages the hanger
			actuator.set(DoubleSolenoid.Value.kReverse);
		}
		
		/**
		 * Disengage the hanger / extend the actuators
		 */
		public void Extend(){
			//TODO: Verify that kForward disengages the hanger
			actuator.set(DoubleSolenoid.Value.kForward);
		}
		
		public boolean isStowed(){
			//TODO: Verify that kForward disengages the hanger
			return actuator.get() == DoubleSolenoid.Value.kReverse;
		}
		
		public boolean isExtended(){
			//TODO: Verify that kForward disengages the hanger
			return actuator.get() == DoubleSolenoid.Value.kForward;
		}
}
