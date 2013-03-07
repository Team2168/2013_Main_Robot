package frc2168_2013.subsystems;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.RobotMap;

public class Arm extends Subsystem {
	
	//define pneumatics variables
	DoubleSolenoid shortCylinder;
	DoubleSolenoid longCylinder;
	
	public Arm(){
		//initialize variables 
		shortCylinder = new DoubleSolenoid(RobotMap.shortArmCylinderExtend,
											RobotMap.shortArmCylinderRetraact);
		longCylinder = new DoubleSolenoid(RobotMap.longArmCylinderExtend,
											RobotMap.longArmCylinderRetract);
	}

	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		//No default command (leave arm where it is)
	}
	
	/**
	 * Retract both cylinders for stow position
	 */
	public void stowPosition(){
		shortCylinder.set(DoubleSolenoid.Value.kReverse);
		longCylinder.set(DoubleSolenoid.Value.kReverse);
	}
	
	/**
	 * Extend the long cylinder to achieve auto and front pyramid angle
	 */	
	public void autoAndFrontPyramid(){
		shortCylinder.set(DoubleSolenoid.Value.kReverse);
		longCylinder.set(DoubleSolenoid.Value.kForward);
	}
	
	/**
	 * Extend both to achieve wall and top pyramid angle
	 */
	public void wallAndTopPyramid(){
		shortCylinder.set(DoubleSolenoid.Value.kForward);
		longCylinder.set(DoubleSolenoid.Value.kForward);
	}
}
