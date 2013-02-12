package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.OI;
import frc2168_2013.RobotMap;
import frc2168_2013.PIDController.Controller.PIDPosition;
import frc2168_2013.PIDController.Controller.PIDSpeed;
import frc2168_2013.PIDController.Sensors.AverageEncoder;
import frc2168_2013.PIDController.TCPStream.TCPsocketSender;
import frc2168_2013.commands.DriveDrivetrainWithJoystick;

public class RightDriveTrain extends Subsystem {
	
	double leftSpeed = 0;
	double rightSpeed = 0;
	
	//declare drivetrain motor controller
	Talon rightDriveMotor;

	//declare sensor
	AverageEncoder rightEncoder;
	
	//declare position controller
	public PIDPosition rightPosController;
	
	//declare speed controller
	public PIDSpeed rightSpeedController;
	
	//declare TCP severs...ONLY FOR DEBUGGING PURPOSES, SHOULD BE REMOVED FOR COMPITITION
	TCPsocketSender TCPrightPosController;
	TCPsocketSender TCPrightSpeedController;
	
	/**
	 * The default constructor for the Drivetrain subsystem.
	 */
    public RightDriveTrain(){
    	System.out.println("drive train encoder shit:" + RobotMap.driveEncoderPulsePerRot);
    	
    	//intializing motor controller using PWM. Refer to RobotMap
    	rightDriveMotor = new Talon (RobotMap.rightDriveMotor);
    	
    	//initialized right and left drive train encoders
    	rightEncoder = new AverageEncoder(RobotMap.rightDriveEncoderChannelA, RobotMap.rightDriveEncoderChannelB, RobotMap.driveEncoderPulsePerRot,RobotMap.driveEncoderDistPerTick, RobotMap.rightDriveTrainEncoderReverse, RobotMap.driveEncodingType, RobotMap.driveSpeedReturnType, RobotMap.drivePosReturnType, RobotMap.driveAvgEncoderVal);
    	rightEncoder.setMaxPeriod(RobotMap.driveEncoderMinPeriod);//min period before reported stopped
    	rightEncoder.setMinRate(RobotMap.driveEncoderMinRate);//min rate before reported stopped
    	rightEncoder.start();
    	
    	//Spawn new PID Controller
    	rightSpeedController = new PIDSpeed("RightSpeedController", RobotMap.driveTrainRightSpeedP, RobotMap.driveTrainRightSpeedI, RobotMap.driveTrainRightSpeedD, rightEncoder, RobotMap.driveTrainPIDPeriod);
    	rightPosController = new PIDPosition("RightPositionController", RobotMap.driveTrainRightPositionP, RobotMap.driveTrainRightPositionI, RobotMap.driveTrainRightPositionD, rightEncoder, RobotMap.driveTrainPIDPeriod);
    	
    	//TODO: add min and max output defaults and set array size
    	
    	//start controller threads
    	rightSpeedController.startThread();
    	rightPosController.startThread();
    	
    	//start TCP Servers for DEBUGING ONLY
    	TCPrightPosController = new TCPsocketSender(RobotMap.TCPServerRightDrivetrainPos, rightPosController);
    	TCPrightPosController.start();
    	
    	TCPrightSpeedController = new TCPsocketSender(RobotMap.TCPServerRightDrivetrainSpeed, rightSpeedController);
    	TCPrightSpeedController.start();
    	
    	//TODO: initialize encoders and closed loop control of drivetrain
    }
	
    /**
     * Initialize the default command for the drivetrain subsystem.
     */
	public void initDefaultCommand() {
		setDefaultCommand(new DriveDrivetrainWithJoystick());
    }
	
	/**
	 * Drive drive train using tank drive
	 * 
	 * @param rightSpeed speed for right motors (1 to -1)
	 */
    public void tankDriveRight(double rightSpeed) {
    	
    	//RobotMap defines which motors are inverted on drivetrain.
    	if(OI.rInvert) {
    		rightSpeed = -rightSpeed;
    	}
    	
    	this.rightSpeed = rightSpeed;
    	
    	
    	//TODO: add hooks for falcon claw
    	//TODO: add interpolation method to adjust sensitivity
    	
    	rightDriveMotor.set(rightSpeed);
    	
    }
    /**
     * drive the right side
     * @param rightSpeed between -1 and 1
     */
    public void driveRight(double rightSpeed) {
    	this.rightSpeed = rightSpeed;
    
    	
    	//RobotMap defines which motors are inverted on drivetrain.
    	if(OI.rInvert) {
    		rightSpeed = -rightSpeed;
    	}     	
    	//TODO: add hooks for falcon claw
    	//TODO: add interpolation method to adjust sensitivity
    	
    	rightDriveMotor.set(rightSpeed);
    }
  
    /**
     * Returns the speed for the right wheels on the drivetrain.
     * 
     * @return right drivetrain wheel speed in ft/s
     */
    public double getRightSpeed() {
    	//TODO: write code for this method
    	return rightSpeed;
    }
}

