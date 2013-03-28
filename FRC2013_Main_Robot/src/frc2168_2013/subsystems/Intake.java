package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.RobotMap;

public class Intake extends Subsystem {		
	
	Relay actuator;
	
	public Intake() {
		//TODO: If an intake is added, flesh this stuff out.
		//actuator = new DoubleSolenoid(RobotMap.intakeExtend,
        //       RobotMap.intakeRetract);
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
	public void lower(){
		//TODO: Verify that kForward disengages the hanger
		//actuator.set(DoubleSolenoid.Value.kForward);
	}
    
    /**
     * Raise the intake mechanism.
     */
	public void raise() {
		//TODO: Verify that kForward engages the hanger
		//actuator.set(DoubleSolenoid.Value.kReverse);
	}
}
