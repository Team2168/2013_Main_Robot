package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.RobotMap;
import frc2168_2013.commands.subSystems.Intake.SetIntakeSpeedPWM;

public class Intake extends Subsystem {		
	
	DoubleSolenoid actuator;
	
	
	public Intake()
	{
		actuator = new DoubleSolenoid(RobotMap.intakeExtend,
                RobotMap.intakeRetract);
		
		
	}

	/**
	 * Set the default command
	 */
	
	public void initDefaultCommand() {
		//Set the default command to stop the rollers
		setDefaultCommand(new SetIntakeSpeedPWM(0.0, 0.0));
		
		//TODO: Should probably also stow the intake.
    }
    
	/**
	 * Set the speed of the intake rollers in PWM units (1.0 to -1.0).
	 * 
	 * @param upperRollerSpeed the PWM speed for the upper roller
	 * @param LowerRollerSpeed the PWM speed for the lower roller
	 */
	public void setPWM(double upperRollerSpeed, double LowerRollerSpeed) {
		//TODO: write the code for this method
	}
	
	/**
	 * Set the speed of the intake rollers in RPM
	 * 
	 * @param upperRollerSpeed the speed of the upper roller (RPM)
	 * @param LowerRollerSpeed the speed of the lower roller (RPM)
	 */
    public void setSpeed(double upperRollerSpeed, double LowerRollerSpeed) {
		//TODO: write the code for this method    	
    }
    
    /**
     * Lower the intake mechanism.
     */
	public void Extend(){
		//TODO: Verify that kForward disengages the hanger
		actuator.set(DoubleSolenoid.Value.kForward);
	}
    
    /**
     * Lower/deploy the intake mechanism.
     */
	public void Stow() {
		//TODO: Verify that kForward engages the hanger
		actuator.set(DoubleSolenoid.Value.kReverse);
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
