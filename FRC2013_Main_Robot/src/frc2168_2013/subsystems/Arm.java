package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.OI;
import frc2168_2013.RobotMap;
import frc2168_2013.PIDController.Controller.PIDPosition;
import frc2168_2013.PIDController.Sensors.AverageEncoder;
import frc2168_2013.PIDController.TCPStream.TCPsocketSender;
import frc2168_2013.commands.DriveArmWithJoystick;
import frc2168_2013.commands.DriveWithJoystick;

public class Arm extends Subsystem {
	
	double armSpeed = 0;
	
	//TODO: Add switches for end of travel safety
	
	//initialized PID Position Controller
	Talon armMotorL;
	Talon armMotorR;
	AverageEncoder armEncoder;
	public PIDPosition armPosController;
	TCPsocketSender TCParmPosController;

	private double speed;
	
	public Arm(){
		armMotorL = new Talon(RobotMap.leftArmMotor);
		armMotorR = new Talon(RobotMap.rightArmMotor);
		armEncoder = new AverageEncoder(RobotMap.armEncoderChannelA, RobotMap.armEncoderChannelB, RobotMap.armEncoderPulsePerRot,RobotMap.armEncoderDistPerTick,RobotMap.armEncoderReverse, RobotMap.armEncodingType, RobotMap.armSpeedReturnType, RobotMap.armPosReturnType,RobotMap.armAvgEncoderVal);
		armEncoder.setMaxPeriod(RobotMap.armEncoderMinPeriod);//min period before reported stopped
		armEncoder.setMinRate(RobotMap.armEncoderMinRate);//min rate before reported stopped
		armEncoder.start();
		
		
		//initialized PID Position Controller
		armPosController = new PIDPosition("ArmPositionController", RobotMap.armPosP, RobotMap.armPosI, RobotMap.armPosD, armEncoder, RobotMap.armPIDPeriod);
		armPosController.setSIZE(RobotMap.armPIDArraySize);
		armPosController.startThread();
		
		//initialized TCP Server for arm position controller, ONLY FOR DEBUDDING, REMOVE FOR COMPETITION
		TCParmPosController = new TCPsocketSender(RobotMap.TCPServerArmPos, armPosController);
		TCParmPosController.start();
	}

	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new DriveArmWithJoystick());

	}

	/**
	 * Get the angle of the arm.
	 * 
	 * @return the angle of the arm in degrees
	 */
	public double getArmAngle(){
		return armEncoder.getPos();
		
	}
	
	
	public void setArmPWM(double armSpeed) {
    	
    	this.armSpeed = armSpeed;
    	
    	//OI defines which motors are inverted
    	if(OI.ainvert)
    		armSpeed = -armSpeed;
    	
    	armMotorL.set(armSpeed);
    	armMotorR.set(-armSpeed); //automatically invert right side from left side
    	
    }

	/**
	 * Set the arm angle.
	 * 
	 * @param angle angle of the arm it should be at degrees
	 */
	public void setArmAngle(double angle){
		//TODO: write the code for this method
	}
	
	/**
	 * Raise the arm
	 */
	public void raiseArm(){
		//TODO: write the code for this method
	}
	/*
	 * Lower the arm
	 */
	public void lowerArm(){
		//TODO: write the code for this method
	}
}
