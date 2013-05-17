package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.RobotMap;
import frc2168_2013.commands.subSystems.Intake.DriveIntakeConstant;

public class IntakeSpeed extends Subsystem {		

	Talon motorR, motorL;
	DigitalInput limitSensorR, limitSensorL;

	double left = 0.0;
	double right = 0.0;
	double leftHopper = 0.0;
	double rightHopper = 0.0;
	int disc;

	public IntakeSpeed() {
		//TODO: If an intake is added, flesh this stuff out.
		motorR = new Talon(RobotMap.intakeMotorR);
		motorL = new Talon(RobotMap.intakeMotorL);
		limitSensorR = new DigitalInput(RobotMap.intakeLimitSensorR);
		limitSensorL = new DigitalInput(RobotMap.intakeLimitSensorL);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveIntakeConstant(0.0, 0.0));
	}

	/**
	 * if right switch is pressed
	 * @return true
	 */
	public boolean intakeRFull(){
		return !limitSensorR.get();
	}

	/**
	 * if left switch is pressed
	 * @return true
	 */
	public boolean intakeLFull(){
		return !limitSensorL.get();
	}

	/**
	 * Drive both sides, will stop if intake is full
	 * @param left speed
	 * @param right speed
	 */
	public void driveIntake(double left, double right){
		driveIntakeRight(right);
		driveIntakeLeft(left);
	}

	/**
	 * drive the left side of the intake unless left switch is pressed, invert left
	 * @param left side speed
	 */
	public void driveIntakeLeft(double left) {

		if(intakeLFull()){
			motorL.set(0.0);
		} else {		
			left = -left;
			this.left = left; 	
			motorL.set(left);
		}
	}

	/**
	 * drive the right side of the intake unless right switch is pressed
	 * @param right side speed
	 */
	public void driveIntakeRight(double right) {

		if(intakeRFull()){
			motorR.set(0.0);
		} else {
			motorR.set(right);
		}
	}

	/**
	 * drive both sides of intake at constant speed, written to unload intake into hopper
	 * @param leftHopper speed
	 * @param rightHopper speed
	 */
	public void driveIntakeHopper(double leftHopper, double rightHopper){
		driveRightHopper(rightHopper);
		driveLeftHopper(leftHopper);
	}

	/**
	 * drive the left side of the intake, invert left
	 * @param leftHopper speed
	 */
	public void driveLeftHopper(double leftHopper) {		

		leftHopper = -leftHopper; 
		this.leftHopper = leftHopper; 	
		motorL.set(leftHopper);

	}


	/**
	 * drive the right side of the intake
	 * @param rightHopper speed
	 */
	public void driveRightHopper(double rightHopper) {

		motorR.set(rightHopper);

	}

	/**
	 * Acquire number of discs in the intake
	 * @return disc count
	 */
	public int getNumberOfDiscs() {

		disc = 0;

		if (intakeRFull()) {
			disc++;
		}
		if (intakeLFull()) {
			disc++;
		}

		return disc;

	}

	/**
	 * If disc count is two, intake full
	 * @return true
	 */
	public boolean intakeFull() {
		if(disc != 2){
			return false;
		} else {
			return true;
		}
	}
}
