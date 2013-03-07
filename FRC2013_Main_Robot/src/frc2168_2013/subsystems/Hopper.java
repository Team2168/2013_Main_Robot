package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;
import frc2168_2013.OI;
import frc2168_2013.RobotMap;
import frc2168_2013.commands.DriveHopperJoystick;

public class Hopper extends Subsystem {
	double speed = 0.0;
	
	Talon hopperMotor;
	DigitalInput disc1, disc2, disc3, disc4;
	Relay teamDiscLight;
	
	public Hopper() {
		hopperMotor = new Talon(RobotMap.hopperMotor);
		disc1 = new DigitalInput(RobotMap.hopperDisc1);
		disc2 = new DigitalInput(RobotMap.hopperDisc2);
		disc3 = new DigitalInput(RobotMap.hopperDisc3);
		disc4 = new DigitalInput(RobotMap.hopperDisc4);
		teamDiscLight = new Relay(RobotMap.teamDiscLight);
	}
	
	protected void initDefaultCommand() {
		//TODO: Add default command.
		setDefaultCommand(new DriveHopperJoystick(0));
	}

	/**
	 * Drives the hopper motor with a PWM input value (1.0 to -1.0).
	 * 
	 * @param speed the speed to drive the motor at (1.0 to -1.0)
	 */
	public void driveHopperPWM(double speed) {
		if(OI.hInvert)
			speed = -speed;
		hopperMotor.set(-speed);
	}

	/**
	 * Detects if disc1 is present. Disc1 is the disc closest to the shooter.
	 * 
	 * @return true if disc1 is present
	 */
	public boolean disc1Present(){
		//TODO: Verify true false for disc presence 
		return !disc1.get();
	}
	
	boolean old1 = false, old2 = false, outputVal = false;
	public boolean disc1PresentDebounced(){
		//Three consecutive samples required for a state change 
		if(!disc1.get() == old1 == old2) {
			outputVal = !disc1.get();
		}
		
		//Update history
		old2 = old1;
		old1 = !disc1.get();
		
		return outputVal;
	}
	
	/**
	 * Detects if disc2 is present. Disc1 is the disc closest to the shooter.
	 * 
	 * @return true if disc2 is present
	 */
	public boolean disc2Present(){
		//TODO: Verify true false for disc presence 
		return !disc2.get();
	}
	/**
	 * Detects if disc3 is present. Disc1 is the disc closest to the shooter.
	 * 
	 * @return true if disc3 is present
	 */
	public boolean disc3Present(){
		//TODO: Verify true false for disc presence
		return !disc3.get();
	}
	/**
	 * Detects if disc4 is present. Disc1 is the disc closest to the shooter.
	 * 
	 * @return true if disc4 is present
	 */
	public boolean disc4Present(){
		//TODO: Verify true false for disc presence
		return !disc4.get();
	}
	
	/**
	 * Return the number of discs present in the hopper.
	 * 
	 * @return the number of discs
	 */
	 public int getNumberOfDiscs() {
	 	int i = 0;
	 	
    		if (disc1Present()) {
    			i++;
    		}
    		if (disc2Present()) {
    			i++;
    		}
    		if (disc3Present()) {
    			i++;
    		}
    		if (disc4Present()) {
    			i++;
    		}
    		
    		return i;
	 }
	 
	 /**
	  * Turn on the team disc light indicator.
	  */
	 public void setDiscLightOn() {
		 //Apply 12V to the + spike output, and gnd to the - spike output
		 teamDiscLight.set(Relay.Value.kForward);
	 }
	 
	 /**
	  * Turn off the team disc light indicator.
	  */
	 public void setDiscLightOff() {
		 teamDiscLight.set(Relay.Value.kOff);
	 }
}
