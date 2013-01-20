package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.RobotMap;
import frc2168_2013.commands.DriveWithJoystick;

public class Drivetrain extends Subsystem {
	
	double leftSpeed = 0;
	double rightSpeed = 0;
	
	Talon rightMotor;
	Talon leftMotor;
	//Jaguar rightMotor;
	//Jaguar leftMotor;	

	/**
	 * The default constructor for the Drivetrain subsystem.
	 */
    public Drivetrain(){
    	rightMotor = new Talon(RobotMap.rightMotor);
    	leftMotor = new Talon(RobotMap.leftMotor);
    	//rightMotor = new Jaguar(RobotMap.rightMotor);
    	//leftMotor = new Jaguar(RobotMap.leftMotor);
    	
    	//TODO: initialize encoders and closed loop control of drivetrain
    }
	
    /**
     * Initialize the default command for the drivetrain subsystem.
     */
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
    }
	
	/**
	 * Sets the speed for the drivetrain motors from joystick values (1.0 to -1.0).
	 * 
	 * @param rightSpeed speed for right motors (1 to -1)
	 * @param leftSpeed speed for left motors (1 to -1)
	 */
    public void setPWM(double rightSpeed, double leftSpeed) {
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
    	
    	rightMotor.set(rightSpeed);
    	leftMotor.set(leftSpeed);
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

