package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.RobotMap;

public class HardStop extends Subsystem {
	DoubleSolenoid stopper;
	
	public HardStop() {
		stopper = new DoubleSolenoid(RobotMap.hopperDiscStopperApply, 
				RobotMap.hopperDiscStopperRelease);
		engageStopper();
	}
	
	/**
	 * Tells the hanger what to do when it starts
	 */
	protected void initDefaultCommand() {
		//No default command!
		//Initialize state once in constructor.
	}
	
	/**
	 * Engage the hard stop.
	 */
	public void engageStopper(){
		stopper.set(DoubleSolenoid.Value.kForward);
	}
	
	/**
	 * Disengage the hard stop
	 */
	public void disengageStopper(){
		stopper.set(DoubleSolenoid.Value.kReverse);
	}
}
