package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.RobotMap;
import frc2168_2013.commands.subSystems.Hanger.HangerDisengage;

public class Hanger extends Subsystem {
	DoubleSolenoid actuator;
	
	public Hanger() {
		actuator = new DoubleSolenoid(RobotMap.hangerDisengage,
		                              RobotMap.hangerEngage);
	}
	
	/**
	 * Tells the hanger what to do when it starts
	 */
	
	protected void initDefaultCommand() {
		setDefaultCommand(new HangerDisengage());
	}
	
	/**
	 * Engage the hanger / retract the actuators.
	 */
	public void engage() {
		//TODO: Verify that kForward engages the hanger
		actuator.set(DoubleSolenoid.Value.kReverse);
	}
	
	/**
	 * Disengage the hanger / extend the actuators
	 */
	public void disengage(){
		//TODO: Verify that kForward disengages the hanger
		actuator.set(DoubleSolenoid.Value.kForward);
	}
}
