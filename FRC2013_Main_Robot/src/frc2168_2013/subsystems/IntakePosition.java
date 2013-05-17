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

	double left = 0.0;
	double right = 0.0;
	double leftLoad = 0.0;
	double rightLoad = 0.0;

	public IntakePosition() {
		//TODO: If an intake is added, flesh this stuff out.
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
	public void Load(){
		actuatorHopper.set(DoubleSolenoid.Value.kForward);
		actuatorFloorload.set(DoubleSolenoid.Value.kForward);
	}


	/**
	 * Raise the intake mechanism to hopper position
	 */
	public void Hopper() {
		actuatorFloorload.set(DoubleSolenoid.Value.kReverse);
		actuatorHopper.set(DoubleSolenoid.Value.kForward);
	}

	/**
	 * Raise the intake mechanism to stow position
	 */
	public void Stow(){
		actuatorHopper.set(DoubleSolenoid.Value.kReverse);
		actuatorFloorload.set(DoubleSolenoid.Value.kReverse);
	}
}
