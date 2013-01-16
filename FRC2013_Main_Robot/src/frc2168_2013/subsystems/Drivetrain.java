package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.RobotMap;

public class Drivetrain extends Subsystem {
	
	double leftspeed = 0;
	double rightspeed = 0;
	Talon rmotor = new Talon(RobotMap.rightMotor);
	Talon lmotor = new Talon(RobotMap.leftMotor);
	
    public Drivetrain(){
    	
    	
    	
    }
	
	public void initDefaultCommand() {
		
		
		
    }
    
    public void setSpeed(double rightspeed, double leftspeed){
    	
    	this.rightspeed = rightspeed;
    	this.leftspeed = leftspeed;
    	
    	rmotor.set(rightspeed);
    	lmotor.set(leftspeed);
    	
    }
    
    public double getrSpeed(){
    	
    	return rmotor.getSpeed();
    	
    }
    
    public double getlSpeed(){
    	
    	return lmotor.getSpeed();
    	
    }

}

