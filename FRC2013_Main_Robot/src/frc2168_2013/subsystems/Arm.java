package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.OI;
import frc2168_2013.RobotMap;
import frc2168_2013.PIDController.Controller.PIDPosition;
import frc2168_2013.PIDController.Controller.PIDPositionArm;
import frc2168_2013.PIDController.Sensors.AverageEncoder;
import frc2168_2013.PIDController.TCPStream.TCPsocketSender;
import frc2168_2013.commands.DriveArmWithJoystick;

public class Arm extends Subsystem {
	
	double armSpeed = 0;
	
	//TODO: Add switches for end of travel safety
	
	//initialized PID Position Controller
	Talon armMotorL;
	Talon armMotorR;
	AverageEncoder armEncoder;
	public PIDPositionArm armPosController;
	TCPsocketSender TCParmPosController;

	AnalogChannel lowHardStop;
	AnalogChannel highHardStop;
	private static final double SWITCH_PRESSED_VOLTAGE  = 3.0;
	
	private double speed;
	
	public Arm(){
		armMotorL = new Talon(RobotMap.leftArmMotor);
		armMotorR = new Talon(RobotMap.rightArmMotor);
		armEncoder = new AverageEncoder(RobotMap.armEncoderChannelA, RobotMap.armEncoderChannelB, RobotMap.armEncoderPulsePerRot,RobotMap.armEncoderDistPerTick,RobotMap.armEncoderReverse, RobotMap.armEncodingType, RobotMap.armSpeedReturnType, RobotMap.armPosReturnType,RobotMap.armAvgEncoderVal);
		armEncoder.setMaxPeriod(RobotMap.armEncoderMinPeriod);//min period before reported stopped
		armEncoder.setMinRate(RobotMap.armEncoderMinRate);//min rate before reported stopped
		armEncoder.start();
		
		//initialized PID Position Controller
		armPosController = new PIDPositionArm("ArmPositionController", RobotMap.armPosP, RobotMap.armPosI, RobotMap.armPosD, armEncoder, RobotMap.armPIDPeriod);
		armPosController.setSIZE(RobotMap.armPIDArraySize);
		armPosController.setRange(15);
		armPosController.setLowAngleDrive(0.6);
		armPosController.setMidAngleDrive(0.2);
		armPosController.setHighAngleDrive(0.4);
		armPosController.setMidAngleThresh(20);
		armPosController.setHighAngleThresh(90);
		armPosController.startThread();
		
		//initialized TCP Server for arm position controller, ONLY FOR DEBUDDING, REMOVE FOR COMPETITION
		TCParmPosController = new TCPsocketSender(RobotMap.TCPServerArmPos, armPosController);
		TCParmPosController.start();
		
		lowHardStop = new AnalogChannel(RobotMap.armLowStop);
		highHardStop = new AnalogChannel(RobotMap.armHighStop);
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
    	
    	//Check the hard stops before sending a value out to the motor
    	//Positive armSpeed = lower arm
    	//Stop raising the arm if it's being commanded up.
    	if (highHardStopPressed() && (armSpeed < 0)) {
    		armSpeed = 0.0;
    	}
    	
    	//Negative armSpeed = raise arm
    	//Stop lowering the arm if it's being commanded down
    	if (lowHardStopPressed() && (armSpeed > 0)) {
    		armSpeed = 0.0;
    	}
    	
    	armMotorL.set(armSpeed);
    	armMotorR.set(-armSpeed); //automatically invert right side from left side
    	
    	//System.out.println(armSpeed);
    	
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
	
	/**
	 * Check if the lower hard stop switch is pressed
	 * @return true if pressed
	 */
	public boolean lowHardStopPressed() {
		return (lowHardStop.getValue() > SWITCH_PRESSED_VOLTAGE);
	}

	/**
	 * Check if the upper hard stop switch is pressed
	 * @return true if pressed
	 */
	public boolean highHardStopPressed() {
		return (highHardStop.getValue() > SWITCH_PRESSED_VOLTAGE);
	}
}
