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

public class LeftDriveTrain extends Subsystem {
	
	double leftSpeed = 0;
	
	//declare drivetrain motor controller
	Talon leftDriveMotor;

	//declare sensor
	AverageEncoder leftEncoder;
	
	//declare position controller
	public PIDPosition leftPosController;
	
	//declare speed controller
	public PIDSpeed leftSpeedController;
	
	//declare TCP severs...ONLY FOR DEBUGGING PURPOSES, SHOULD BE REMOVED FOR COMPITITION
	TCPsocketSender TCPleftPosController;
	TCPsocketSender TCPleftSpeedController;
	
	/**
	 * The default constructor for the Drivetrain subsystem.
	 */
    public LeftDriveTrain(){
    	System.out.println("drive train encoder shit:" + RobotMap.driveEncoderPulsePerRot);
    	
    	//intializing motor controller using PWM. Refer to RobotMap
    	leftDriveMotor = new Talon (RobotMap.leftDriveMotor);
    	
    	//initialized right and left drive train encoders
    	leftEncoder = new AverageEncoder(RobotMap.leftDriveEncoderChannelA, RobotMap.leftDriveEncoderChannelB, RobotMap.driveEncoderPulsePerRot,RobotMap.driveEncoderDistPerTick, RobotMap.leftDriveTrainEncoderReverse, RobotMap.driveEncodingType, RobotMap.driveSpeedReturnType, RobotMap.drivePosReturnType,RobotMap.driveAvgEncoderVal);
    	leftEncoder.setMaxPeriod(RobotMap.driveEncoderMinPeriod);//min period before reported stopped
    	leftEncoder.setMinRate(RobotMap.driveEncoderMinRate);//min rate before reported stopped
    	leftEncoder.start();
    	
    	//Spawn new PID Controller
    	leftSpeedController = new PIDSpeed("LeftSpeedController", RobotMap.driveTrainLeftSpeedP, RobotMap.driveTrainLeftSpeedI, RobotMap.driveTrainLeftSpeedD, leftEncoder, RobotMap.driveTrainPIDPeriod);
    	leftPosController = new PIDPosition("LeftPositionController", RobotMap.driveTrainLeftPositionP, RobotMap.driveTrainLeftPositionI, RobotMap.driveTrainLeftPositionD, leftEncoder, RobotMap.driveTrainPIDPeriod);
    	
    	//TODO: add min and max output defaults and set array size
    	
    	//start controller threads
    	leftSpeedController.startThread();
    	leftPosController.startThread();
    	
    	//start TCP Servers for DEBUGING ONLY
    	TCPleftPosController = new TCPsocketSender(RobotMap.TCPServerLeftDrivetrainPos, leftPosController);
    	TCPleftPosController.start();
    	
    	TCPleftSpeedController = new TCPsocketSender(RobotMap.TCPServerLeftDrivetrainSpeed, leftSpeedController);
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
	 * @param leftSpeed speed for left motors (1 to -1)
	 */
    public void tankDriveLeft(double leftSpeed) {
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
	 * @param leftSpeed speed for the left motors (ft/s)
	 */
	public void setLeftSpeed(double leftSpeed) {
		//TODO: finish the code for this method
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

