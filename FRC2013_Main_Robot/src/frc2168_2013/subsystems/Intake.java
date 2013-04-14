package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.OI;
import frc2168_2013.RobotMap;

public class Intake extends Subsystem {		
	
	Relay actuatorS; //short actuator
	Relay actuatorL; //long actuator
	
	Talon rightRollerTalon;
	Talon leftRollerTalon;
	Victor rightRollerVictor;
	Victor leftRollerVictor;
	
	public Intake() {
		actuatorS = new Relay(RobotMap.intakeS);
		actuatorL = new Relay(RobotMap.intakeL);
		
		if(RobotMap.USE_TALONS_SHOOTER) {
			rightRollerTalon = new Talon(RobotMap.intakeRollerR);
			leftRollerTalon = new Talon(RobotMap.intakeRollerL);
		} else {
			rightRollerVictor = new Victor(RobotMap.intakeRollerR);
			leftRollerVictor = new Victor(RobotMap.intakeRollerL);
		}
	}

	/**
	 * Set the default command
	 */
	
	public void initDefaultCommand() {
		
    }
	
	/**
	 * drives both intake motors
	 * @param leftRollerSpeed left roller which drives slower than the right to avoid jamming
	 * @param rightRollerSpeed drive right roller
	 */
	public void DriveIntakeMotors(double leftRollerSpeed, double rightRollerSpeed){
		driveLeftRoller(leftRollerSpeed);
		driveRightRoller(rightRollerSpeed);
	}
	
    /**
     * drives right roller
     * @param rightRollerSpeed between -1 and 1
     */
	public void driveRightRoller(double rightRollerSpeed) {
    	//RobotMap defines which motors are inverted.
    	if(OI.iRInvert)
    		rightRollerSpeed = -rightRollerSpeed;

    	rightRollerSpeed = OI.minJoystickThreshold(rightRollerSpeed);
    	if(RobotMap.USE_TALONS_SHOOTER) {
    		rightRollerTalon.set(rightRollerSpeed);
    	} else {
    		rightRollerVictor.set(rightRollerSpeed);
    	}
	}
	
	/**
	 * drives left rollers
	 * @param leftRollerSpeed between -0.5 and 0.5
	 */
	public void driveLeftRoller(double leftRollerSpeed) {
    	//RobotMap defines which motors are inverted.
    	if(OI.iLInvert)
    		leftRollerSpeed = -leftRollerSpeed;

    	leftRollerSpeed = OI.minJoystickThreshold(leftRollerSpeed);
    	if(RobotMap.USE_TALONS_SHOOTER) {
    		leftRollerTalon.set(leftRollerSpeed/2); //drive left roller motor slower so the right roller disk exits before the left
    	} else {
    		leftRollerVictor.set(leftRollerSpeed/2); //drive left roller motor slower so the right roller disk exits before the left
    	}
	}
	
	
   /**
    * bring intake to load position
    */
	public void HopperToLoad() {
		//TODO: Verify that kForward and k reverse
		actuatorS.set(Relay.Value.kReverse);
		actuatorL.set(Relay.Value.kReverse);
	}
	
	/**
	 * bring intake to hopper to dump
	 */
	public void LoadToHopper(){
		//TODO: Verify that kForward and k reverse
		actuatorS.set(Relay.Value.kReverse);
		actuatorL.set(Relay.Value.kForward);		
	}
	
	/**
	 * bring intake to stow position for human loading
	 */
	public void HopperToStow(){
		//TODO: Verify that kForward and k reverse
		actuatorS.set(Relay.Value.kForward);
		actuatorL.set(Relay.Value.kForward);		
	}
}
