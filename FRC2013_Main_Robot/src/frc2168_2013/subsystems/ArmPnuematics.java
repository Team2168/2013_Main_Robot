package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.RobotMap;
import frc2168_2013.commands.subSystems.Hanger.HangerDisengage;

public class ArmPnuematics extends Subsystem {
	DoubleSolenoid actuator;
	
	public ArmPnuematics() {
		actuator = new DoubleSolenoid(RobotMap.armExtend,
		                              RobotMap.armRetract);
	}
	
	/**
	 * Tells the hanger what to do when it starts
	 */
	protected void initDefaultCommand() {
		
	}
	
	/**
	 * Engage the hanger / retract the actuators.
	 */
	public void armStow() {
		//TODO: Verify that kForward engages the hanger
		actuator.set(DoubleSolenoid.Value.kReverse);
	}
	
	/**
	 * Disengage the hanger / extend the actuators
	 */
	public void armExtend(){
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
