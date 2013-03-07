package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.OI;
import frc2168_2013.RobotMap;
import frc2168_2013.PIDController.Controller.PIDPosition;
import frc2168_2013.PIDController.Controller.PIDPositionArm;
import frc2168_2013.PIDController.Sensors.AverageEncoder;
import frc2168_2013.PIDController.TCPStream.TCPsocketSender;
import frc2168_2013.commands.DriveArmWithJoystick;
import frc2168_2013.commands.StowArmPosition;

public class Arm extends Subsystem {
	
	//define pneumatics variables
	DoubleSolenoid shortCylinder;
	DoubleSolenoid longCylinder;
	
	public Arm(){
		
		//initialize variables 
		shortCylinder = new DoubleSolenoid(RobotMap.shortArmCylinderRelease,
											RobotMap.shortArmCylinderApply);
		longCylinder = new DoubleSolenoid(RobotMap.longArmCylinderRelease,
				RobotMap.longArmCylinderApply);
		
	}

	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		//Default Command should be to bring it to stow
		setDefaultCommand(new StowArmPosition());
		

	}
	
	/**
	 * set both cylinders to reverse for stow position
	 */
	public void stowPosition(){
		shortCylinder.set(DoubleSolenoid.Value.kReverse);
		longCylinder.set(DoubleSolenoid.Value.kReverse);
	}
	
	/**
	 * fire long cylinder to achieve auto and front pyramid angle
	 */	
	public void autoAndFrontPyramid(){
		shortCylinder.set(DoubleSolenoid.Value.kReverse);
		longCylinder.set(DoubleSolenoid.Value.kForward);
	}
	
	/**
	 * fire bother to achieve wall and top pyramid angle
	 */
	public void wallAndTopPyramid(){
		shortCylinder.set(DoubleSolenoid.Value.kForward);
		longCylinder.set(DoubleSolenoid.Value.kForward);
	}
}
