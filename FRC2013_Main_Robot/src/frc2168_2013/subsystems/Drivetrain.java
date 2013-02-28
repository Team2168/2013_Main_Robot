package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.OI;
import frc2168_2013.RobotMap;
import frc2168_2013.PIDController.Controller.PIDPosition;
import frc2168_2013.PIDController.Controller.PIDSpeed;
import frc2168_2013.PIDController.Sensors.AverageEncoder;
import frc2168_2013.PIDController.TCPStream.TCPsocketSender;
import frc2168_2013.commands.DriveWithJoystick;

public class Drivetrain extends Subsystem {
	
	double leftSpeed = 0;
	double rightSpeed = 0;
	
	//declare sensors
	AverageEncoder rightEncoder;
	AverageEncoder leftEncoder;
	
	//declare position controllers
	public PIDPosition rightPosController;
	public PIDPosition leftPosController;
	
	//declare speed controllers
	public PIDSpeed rightSpeedController;
	public PIDSpeed leftSpeedController;
	
	//declare TCP severs...ONLY FOR DEBUGGING PURPOSES, SHOULD BE REMOVED FOR COMPITITION
//	TCPsocketSender TCPrightPosController;
//	TCPsocketSender TCPrightSpeedController;
//	TCPsocketSender TCPleftPosController;
//	TCPsocketSender TCPleftSpeedController;

	Talon rightTalonDriveMotor;
	Talon leftTalonDriveMotor;

	Victor rightVictorDriveMotor;
	Victor leftVictorDriveMotor;
	
	/**
	 * The default constructor for the Drivetrain subsystem.
	 */
    public Drivetrain(){
    	System.out.println("drive train encoder stuff:" + RobotMap.driveEncoderPulsePerRot);
    
    	//declare drivetrain motor controllers
    	//intializing motor controller using PWM. Refer to RobotMap
    	if(RobotMap.USE_TALONS) {
        	rightTalonDriveMotor = new Talon (RobotMap.rightDriveMotor);
        	leftTalonDriveMotor = new Talon (RobotMap.leftDriveMotor);	
    	} else {
    		rightVictorDriveMotor = new Victor (RobotMap.rightDriveMotor);
        	leftVictorDriveMotor = new Victor (RobotMap.leftDriveMotor);    		
    	}
    	
    	//initialized right and left drive train encoders
    	rightEncoder = new AverageEncoder(RobotMap.rightDriveEncoderChannelA, RobotMap.rightDriveEncoderChannelB, RobotMap.driveEncoderPulsePerRot,RobotMap.driveEncoderDistPerTick, RobotMap.rightDriveTrainEncoderReverse, RobotMap.driveEncodingType, RobotMap.driveSpeedReturnType, RobotMap.drivePosReturnType, RobotMap.driveAvgEncoderVal);
    	rightEncoder.setMaxPeriod(RobotMap.driveEncoderMinPeriod);//min period before reported stopped
    	rightEncoder.setMinRate(RobotMap.driveEncoderMinRate);//min rate before reported stopped
    	rightEncoder.start();
    	
    	leftEncoder = new AverageEncoder(RobotMap.leftDriveEncoderChannelA, RobotMap.leftDriveEncoderChannelB, RobotMap.driveEncoderPulsePerRot,RobotMap.driveEncoderDistPerTick, RobotMap.leftDriveTrainEncoderReverse, RobotMap.driveEncodingType, RobotMap.driveSpeedReturnType, RobotMap.drivePosReturnType,RobotMap.driveAvgEncoderVal);
    	leftEncoder.setMaxPeriod(RobotMap.driveEncoderMinPeriod);//min period before reported stopped
    	leftEncoder.setMinRate(RobotMap.driveEncoderMinRate);//min rate before reported stopped
    	leftEncoder.start();
    	
    	//Spawn new PID Controller
    	rightSpeedController = new PIDSpeed("RightSpeedController", RobotMap.driveTrainRightSpeedP, RobotMap.driveTrainRightSpeedI, RobotMap.driveTrainRightSpeedD, rightEncoder, RobotMap.driveTrainPIDPeriod);
    	rightPosController = new PIDPosition("RightPositionController", RobotMap.driveTrainRightPositionP, RobotMap.driveTrainRightPositionI, RobotMap.driveTrainRightPositionD, rightEncoder, RobotMap.driveTrainPIDPeriod);
    	leftSpeedController = new PIDSpeed("LeftSpeedController", RobotMap.driveTrainLeftSpeedP, RobotMap.driveTrainLeftSpeedI, RobotMap.driveTrainLeftSpeedD, leftEncoder, RobotMap.driveTrainPIDPeriod);
    	leftPosController = new PIDPosition("LeftPositionController", RobotMap.driveTrainLeftPositionP, RobotMap.driveTrainLeftPositionI, RobotMap.driveTrainLeftPositionD, leftEncoder, RobotMap.driveTrainPIDPeriod);
    	
    	//add min and max output defaults and set array size
    	rightSpeedController.setSIZE(RobotMap.drivetrainPIDArraySize);
    	leftSpeedController.setSIZE(RobotMap.drivetrainPIDArraySize);
    	rightPosController.setSIZE(RobotMap.drivetrainPIDArraySize);
    	leftPosController.setSIZE(RobotMap.drivetrainPIDArraySize);    	
    	
    	//start controller threads
    	rightSpeedController.startThread();
    	rightPosController.startThread();
    	leftSpeedController.startThread();
    	leftPosController.startThread();
    	
    	//start TCP Servers for DEBUGING ONLY
//    	TCPrightPosController = new TCPsocketSender(RobotMap.TCPServerRightDrivetrainPos, rightPosController);
//    	TCPrightPosController.start();
//    	
//    	TCPrightSpeedController = new TCPsocketSender(RobotMap.TCPServerRightDrivetrainSpeed, rightSpeedController);
//    	TCPrightSpeedController.start();
//    	
//    	TCPleftPosController = new TCPsocketSender(RobotMap.TCPServerLeftDrivetrainPos, leftPosController);
//    	TCPleftPosController.start();
//    	
//    	TCPleftSpeedController = new TCPsocketSender(RobotMap.TCPServerLeftDrivetrainSpeed, leftSpeedController);
//    	TCPleftSpeedController.start();
//    	
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
    	driveRight(rightSpeed);
    	driveLeft(leftSpeed);
    }
    
    /**
     * drive the right side
     * @param rightSpeed between -1 and 1
     */
    public void driveRight(double rightSpeed) {
    	//RobotMap defines which motors are inverted on drivetrain.
    	if(OI.rInvert) {
    		rightSpeed = -rightSpeed;
    	}

    	rightSpeed = minSpeedThreshold(rightSpeed);
    	
    	this.rightSpeed = rightSpeed;
    	
    	if(RobotMap.USE_TALONS) {
    		rightTalonDriveMotor.set(rightSpeed);
    	} else {
    		rightVictorDriveMotor.set(rightSpeed);
    	}
    }
    
    /**
     * drive the left side
     * @param leftSpeed between -1 and 1
     */
    public void driveLeft(double leftSpeed) {
    	//RobotMap defines which motors are inverted on drivetrain.
    	if(OI.lInvert) {
    		leftSpeed = -leftSpeed;
    	}
    	
    	leftSpeed = minSpeedThreshold(leftSpeed);
    	
    	this.leftSpeed = leftSpeed;
    	
    	if(RobotMap.USE_TALONS) {
    		rightTalonDriveMotor.set(leftSpeed);
    	} else {
    		rightVictorDriveMotor.set(leftSpeed);
    	}
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
    
    /**
     * A minimum threshold function. The command to the motor has to exceed a
     * certain value for it to be sent.
     * 
     * @param speed The input value
     * @return the adjusted speed value
     */
    private double minSpeedThreshold(double speed) {
    	double mySpeed = 0.0;
    	
    	//Need a voltage greater than the below value for a value to be sent
    	//  out to the motor.
    	//Empirically have seen 0.057 being sent out with stick centered.
    	if(Math.abs(speed) > 0.06) {
    		mySpeed = speed;
    	}
    
    	return mySpeed;
    }
    
    /**
     * Zero the distance travelled by the drivetrain.
     */
    public void resetDistance() {
    	//TODO: zero the encoder distance
    }
    
    /**
     * Get the accumulated distance traveled since the last reset.
     * 
     * @return distance in inches
     */
    public double getDistance(){
    	//TODO: get the accumulated distance traveled since the last reset
    	return 0;
    }
}

