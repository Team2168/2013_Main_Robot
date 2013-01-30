package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.RobotMap;
import frc2168_2013.PIDController.Controller.PIDPosition;
import frc2168_2013.PIDController.Controller.PIDSpeed;
import frc2168_2013.PIDController.Sensors.AverageEncoder;
import frc2168_2013.PIDController.TCPStream.TCPsocketSender;
import frc2168_2013.commands.DriveWithJoystick;

public class Drivetrain extends Subsystem {
	
	double leftSpeed = 0;
	double rightSpeed = 0;
	
	//declared drivetrain motor controllers
	Talon rightDriveMotor;
	Talon leftDriveMotor;

	//declared sensors
	AverageEncoder rightEncoder;
	AverageEncoder leftEncoder;
	
	//declared position controllers
	PIDPosition rightPosController;
	PIDPosition leftPosController;
	
	//declared speed controllers
	PIDSpeed rightSpeedController;
	PIDSpeed leftSpeedController;
	
	//declared TCP severs...ONLY FOR DEBUGGING PURPOSES, SHOULD BE REMOVED FOR COMPITITION
	TCPsocketSender TCPrightPosController;
	TCPsocketSender TCPrightSpeedController;
	TCPsocketSender TCPleftPosController;
	TCPsocketSender TCPleftSpeedController;
	
	/**
	 * The default constructor for the Drivetrain subsystem.
	 */
    public Drivetrain(){
    	
    	//intializing motor controller using PWM. Refer to RobotMap
    	rightDriveMotor = new Talon (RobotMap.rightDriveMotor);
  	leftDriveMotor = new Talon (RobotMap.leftDriveMotor);
    	
    	//initialized right and left drive train encoders
    	rightEncoder = new AverageEncoder(RobotMap.rightDriveEncoderChannelA, RobotMap.rightDriveEncoderChannelB, RobotMap.rightDriveTrainEncoderReverse, CounterBase.EncodingType.k1X, RobotMap.driveAvgEncoderVal);
    	rightEncoder.setDistancePerPulse(RobotMap.driveEencoderDistPerTick);
    	rightEncoder.setMaxPeriod(RobotMap.driveEncoderMinPeriod);//min period before reported stopped
    	rightEncoder.setMinRate(RobotMap.driveEncoderMinRate);//min rate before reported stopped
    	rightEncoder.start();
    	
    	leftEncoder = new AverageEncoder(RobotMap.leftDriveEncoderChannelA, RobotMap.leftDriveEncoderChannelB, RobotMap.leftDriveTrainEncoderReverse, CounterBase.EncodingType.k1X, RobotMap.driveAvgEncoderVal);
    	leftEncoder.setDistancePerPulse(RobotMap.driveEencoderDistPerTick);
    	leftEncoder.setMaxPeriod(RobotMap.driveEncoderMinPeriod);//min period before reported stopped
    	leftEncoder.setMinRate(RobotMap.driveEncoderMinRate);//min rate before reported stopped
    	leftEncoder.start();
    	
    	//Spawn new PID Controller
    	rightSpeedController = new PIDSpeed("RightSpeedController", RobotMap.driveTrainRightSpeedP, RobotMap.driveTrainRightSpeedI, RobotMap.driveTrainRightSpeedD, rightEncoder, RobotMap.driveTrainPIDPeriod);
    	rightPosController = new PIDPosition("RightPositionController", RobotMap.driveTrainRightPositionP, RobotMap.driveTrainRightPositionI, RobotMap.driveTrainRightPositionD, rightEncoder, RobotMap.driveTrainPIDPeriod);
    	leftSpeedController = new PIDSpeed("LeftSpeedController", RobotMap.driveTrainLeftSpeedP, RobotMap.driveTrainLeftSpeedI, RobotMap.driveTrainLeftSpeedD, leftEncoder, RobotMap.driveTrainPIDPeriod);
    	leftPosController = new PIDPosition("LeftPositionController", RobotMap.driveTrainLeftPositionP, RobotMap.driveTrainLeftPositionI, RobotMap.driveTrainLeftPositionD, leftEncoder, RobotMap.driveTrainPIDPeriod);
    	
    	//add min and max outputs and set array size
    	
    	//start encoder threads
    	rightSpeedController.startThread();
    	rightPosController.startThread();
    	leftSpeedController.startThread();
    	leftPosController.startThread();
    	
    	//start TCP Servers for DEBUGING ONLY
    	TCPrightPosController = new TCPsocketSender(1180, rightPosController);
    	TCPrightPosController.start();
    	
    	TCPrightSpeedController = new TCPsocketSender(1181, rightSpeedController);
    	TCPrightSpeedController.start();
    	
    	TCPleftPosController = new TCPsocketSender(1182, leftPosController);
    	TCPleftPosController.start();
    	
    	TCPleftSpeedController = new TCPsocketSender(1183, leftSpeedController);
    	TCPleftSpeedController.start();
    	
    	//TODO: initialize encoders and closed loop control of drivetrain
    }
	
    /**
     * Initialize the default command for the drivetrain subsystem.
     */
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
    }
	
	/**
	 * Drive drive train using tank drive
	 * 
	 * @param rightSpeed speed for right motors (1 to -1)
	 * @param leftSpeed speed for left motors (1 to -1)
	 */
    public void tankDrive(double rightSpeed, double leftSpeed) {
    	this.rightSpeed = rightSpeed;
    	this.leftSpeed = leftSpeed;
    	
    	//RobotMap defines which motors are inverted on drivetrain.
    	if(RobotMap.rInvert) {
    		rightSpeed = -rightSpeed;
    	} else if(RobotMap.lInvert) {
    		leftSpeed = -leftSpeed;
    	}
    	
    	//TODO: add hooks for falcon claw
    	//TODO: add interpolation method to adjust sensitivity
    	
    	leftDriveMotor.set(leftSpeed);
    	rightDriveMotor.set(rightSpeed);
    	
    }
    /**
     * drive the right side
     * @param rightSpeed between -1 and 1
     */
    public void driveRight(double rightSpeed) {
    	this.rightSpeed = rightSpeed;
    
    	
    	//RobotMap defines which motors are inverted on drivetrain.
    	if(RobotMap.rInvert) {
    		rightSpeed = -rightSpeed;
    	}     	
    	//TODO: add hooks for falcon claw
    	//TODO: add interpolation method to adjust sensitivity
    	
    	rightDriveMotor.set(rightSpeed);
    }
    
    /**
     * drive the left side
     * @param leftSpeed between -1 and 1
     */
    public void driveLeft(double leftSpeed) {
    	
    	this.leftSpeed = leftSpeed;
    	
    	//RobotMap defines which motors are inverted on drivetrain.
    	if(RobotMap.lInvert) {
    		leftSpeed = -leftSpeed;
    	}
    	
    	//TODO: add hooks for falcon claw
    	//TODO: add interpolation method to adjust sensitivity
    	
    	leftDriveMotor.set(leftSpeed);
    	
    }
    
	/**
	 * Sets the speed for the drivetrain motors (ft/s)
	 * 
	 * @param rightSpeed speed for the right motors (ft/s)
	 * @param leftSpeed speed for the left motors (ft/s)
	 */
	public void setSpeed(double rightSpeed, double leftSpeed) {
		//TODO: finish the code for this method
	}
    
    /**
     * Returns the speed for the right wheels on the drivetrain.
     * 
     * @return right drivetrain wheel speed in ft/s
     */
    public double getRightSpeed() {
    	//TODO: write code for this method
    	return 0.0;
    }
    
    /**
     * Returns the speed for the left wheels on the drivetrain.
     * 
     * @return left drivetrain wheel speed in ft/s
     */
    public double getLeftSpeed(){
    	//TODO: write code for this method.
    	return 0.0;
    }

}

