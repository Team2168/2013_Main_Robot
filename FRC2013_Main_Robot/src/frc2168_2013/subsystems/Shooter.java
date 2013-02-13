package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.OI;
import frc2168_2013.RobotMap;
import frc2168_2013.PIDController.Controller.PIDSpeed;
import frc2168_2013.PIDController.Sensors.AverageEncoder;
import frc2168_2013.PIDController.TCPStream.TCPsocketSender;
import frc2168_2013.commands.SetShooterSpeedPWM;

public class Shooter extends Subsystem {
	Talon shooterMotor;
	AverageEncoder shooterWheelEncoder;
	PIDSpeed shooterWheelSpeedController;
	TCPsocketSender TCPshooterSpeedController;

	
	public Shooter() {
		shooterMotor = new Talon(RobotMap.shooterMotor);

		
		//Set Encoder Parameters
		shooterWheelEncoder = new AverageEncoder(RobotMap.shooterEncoderChannelA, RobotMap.shooterEncoderChannelB, RobotMap.shooterEncoderPulsePerRot,RobotMap.shooterEncoderDistPerTick,RobotMap.shooterEncoderReverse, RobotMap.shooterEncodingType, RobotMap.shooterSpeedReturnType, RobotMap.shooterPosReturnType,RobotMap.shooterAvgEncoderVal);
		shooterWheelEncoder.setMinRate(RobotMap.shooterEncoderMinRate);
		shooterWheelEncoder.setMaxPeriod(RobotMap.shooterEncoderMinPeriod);
		shooterWheelEncoder.start();

		
		shooterWheelSpeedController = new PIDSpeed("ShooterSpeedController", RobotMap.shooterSpeedP, RobotMap.shooterSpeedI, RobotMap.shooterSpeedD, shooterWheelEncoder, RobotMap.shooterPIDPeriod);
		shooterWheelSpeedController.setSIZE(RobotMap.shooterPIDArraySize);
		shooterWheelSpeedController.startThread();

		//initialized TCP Server, ONLY FOR DEBUDDING, REMOVE FOR COMPETITION
		TCPshooterSpeedController = new TCPsocketSender(RobotMap.TCPServerShooterSpeed, shooterWheelSpeedController);
		TCPshooterSpeedController.start();
	}
	
	/**
	 * Set the default command for this subsystem.
	 */
    public void initDefaultCommand() {
    	//Set the shooter wheels to not spin.
    	setDefaultCommand(new SetShooterSpeedPWM(0.0));
    }
   
    /**
     * Set the speeds of the shooter wheel. Where wheel1 is the first wheel
     * the disc will hit when shot.
     * 
     * @param wheel1 Speed for wheel 1 (1.0 to -1.0)
     */
    public void setPWM(double speed) {
       
        
        //RobotMap defines which motors are inverted on drivetrain.
        if(OI.sInvert)
        	speed = -speed;
        
       
        shooterMotor.set(speed);

    }
    
    /**
     * Get the speed of the shooter.
     * 
     * @return the speed of the shooter (inches/seconds)
     */
    public double getSpeed() {
    	//TODO: Write code for this method
    	return shooterWheelEncoder.getRate();
    }
    
    /**
     * Check to see if the speed controller has reached its set point.
     * 
     * @return true if the shooter wheel is up to speed, false otherwise
     */
    public boolean atSpeed() {
    	//TODO: Verify the doesn't return true when the wheel speed is real low (0.0)
    	return shooterWheelSpeedController.isFinished();
    }
    
    /**
     * Stops the encoder from counting.
     */
    public void stopEncoder() {
    	shooterWheelEncoder.stop();
    }
    
    /**
     * Resets the encoder distance to zero.
     */
    public void resetEncoder() {
    	shooterWheelEncoder.reset();
    }
    
    public void startEncoder() {
    	shooterWheelEncoder.start();
    }
}

