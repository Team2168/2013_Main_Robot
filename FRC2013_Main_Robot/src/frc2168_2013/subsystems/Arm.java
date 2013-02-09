package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.RobotMap;
import frc2168_2013.PIDController.Controller.PIDPosition;
import frc2168_2013.PIDController.Sensors.AverageEncoder;
import frc2168_2013.PIDController.TCPStream.TCPsocketSender;

public class Arm extends Subsystem {
	
	//initialized PID Position Controller
	Talon armMotor;
	AverageEncoder armEncoder;
	PIDPosition armPosController;
	TCPsocketSender TCParmPosController;
	
	public Arm(){
		armMotor = new Talon(RobotMap.armMotor);
		armEncoder = new AverageEncoder(RobotMap.armEncoderChannelA, RobotMap.armEncoderChannelB, RobotMap.armPulsePerRotation,RobotMap.armEncoderReverse, CounterBase.EncodingType.k1X, RobotMap.armAvgEncoderVal);
		armEncoder.setDistancePerPulse(RobotMap.armEncoderDistPerTick);
		armEncoder.setMaxPeriod(RobotMap.armEncoderMinPeriod);//min period before reported stopped
		armEncoder.setMinRate(RobotMap.armEncoderMinRate);//min rate before reported stopped
		armEncoder.start();
		
		
		//initialized PID Position Controller
		armPosController = new PIDPosition("ArmPositionController", RobotMap.armPosP, RobotMap.armPosI, RobotMap.armPosD, armEncoder, RobotMap.armPIDPeriod);
		armPosController.startThread();
		
		//initialized TCP Server for arm position controller, ONLY FOR DEBUDDING, REMOVE FOR COMPETITION
		TCParmPosController = new TCPsocketSender(RobotMap.TCPServerArmPos, armPosController);
		TCParmPosController.start();
	}

	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	/**
	 * Get the angle of the arm.
	 * 
	 * @return the angle of the arm in degrees
	 */
	public double getArmAngle(){
		return 0;
		
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
