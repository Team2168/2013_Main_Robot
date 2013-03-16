package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.RobotMap;

public class ShooterAngle extends Subsystem {
	DoubleSolenoid actuator;
	
	public ShooterAngle() {
		actuator = new DoubleSolenoid(RobotMap.shooterAngleExtend,
		                              RobotMap.shooterAngleRetract);
	}
	
	/**
	 * Tells the Shooter Angle what to do when it starts
	 */
	
	protected void initDefaultCommand() {
		
	}
	
	/**
	 * Engage the Shooter Angle / retract the actuators.
	 */
	public void Stow() {
		//TODO: Verify that kForward engages the Shooter Angle
		actuator.set(DoubleSolenoid.Value.kReverse);
	}
	
	/**
	 * Disengage the Shooter Angle / extend the actuators
	 */
	public void Extend(){
		//TODO: Verify that kForward disengages the Shooter Angle
		actuator.set(DoubleSolenoid.Value.kForward);
	}
	
	public boolean isStowed(){
		//TODO: Verify that kForward disengages the Shooter Angle
		return actuator.get() == DoubleSolenoid.Value.kReverse;
	}
	
	public boolean isExtended(){
		//TODO: Verify that kForward disengages the Shooter Angle
		return actuator.get() == DoubleSolenoid.Value.kForward;
	}
}
