package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.RobotMap;

public class IntakePosition extends Subsystem {	

	DoubleSolenoid actuatorHopper, actuatorFloorload;
	Talon intakeMotorR, intakeMotorL;
	DigitalInput intakeLimitSensorR, intakeLimitSensorL;

	public IntakePosition() {
		actuatorHopper = new DoubleSolenoid(2, RobotMap.intakeHopperExtend,
				RobotMap.intakeHopperRetract);
		actuatorFloorload = new DoubleSolenoid(2, RobotMap.intakeFloorloadExtend,
				RobotMap.intakeFloorloadRetract);
	}

	public void initDefaultCommand() {

	}

	/**
	 * Lower intake to load position
	 */
	public void load(){
		actuatorHopper.set(DoubleSolenoid.Value.kReverse);
		actuatorFloorload.set(DoubleSolenoid.Value.kReverse);
	}


	/**
	 * Raise the intake mechanism to hopper position
	 */
	public void hopper() {
		actuatorHopper.set(DoubleSolenoid.Value.kReverse);
		actuatorFloorload.set(DoubleSolenoid.Value.kForward);
	}

	/**
	 * Raise the intake mechanism to stow position
	 */
	public void stow(){
		actuatorHopper.set(DoubleSolenoid.Value.kForward);
		actuatorFloorload.set(DoubleSolenoid.Value.kForward);
	}
	
}
