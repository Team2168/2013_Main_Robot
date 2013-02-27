package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.RobotMap;
import frc2168_2013.commands.HangerDisengage;

public class HardStop extends Subsystem {
	DoubleSolenoid stopper;
	
	public HardStop() {
		stopper = new DoubleSolenoid(RobotMap.hopperDiscStopperApply, 
				RobotMap.hopperDiscStopperRelease);
		stopper.set(DoubleSolenoid.Value.kForward);
	}
	
	/**
	 * Tells the hanger what to do when it starts
	 */
	protected void initDefaultCommand() {
		setDefaultCommand(new HangerDisengage());
	}
	
	/**
	 * Disengage the hard stop / retract the actuator.
	 */
	public void disengageStopper(){
		stopper.set(DoubleSolenoid.Value.kReverse);
	}
	
	/**
	 * Engage the hard stop / extend the actuator
	 */
	public void engageStopper(){
		stopper.set(DoubleSolenoid.Value.kForward);
	}
	
}
