package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.RobotMap;

public class Drivetrain extends Subsystem {
	
	double leftspeed = 0;
	double rightspeed = 0;
	Talon rmotor0 = new Talon(RobotMap.rightMotor0);
	Talon rmotor1 = new Talon(RobotMap.rightMotor1);
	Talon rmotor2 = new Talon(RobotMap.rightMotor2);
	Talon lmotor0 = new Talon(RobotMap.leftMotor0);
	Talon lmotor1 = new Talon(RobotMap.leftMotor1);
	Talon lmotor2 = new Talon(RobotMap.leftMotor2);
	
	
	
    public Drivetrain(){
    	
    	
    	
    }
	
	public void initDefaultCommand() {
		
		
		
    }
    
    public void setSpeed(double rightspeed, double leftspeed){
    	
    	this.rightspeed = rightspeed;
    	this.leftspeed = leftspeed;
    	
    	if(RobotMap.rInvert){
    		
    		rightspeed = -rightspeed;
    		
    	} else if(RobotMap.lInvert){
    		
    		leftspeed = -leftspeed;
    		
    	} else {
    	
    		rmotor0.set(rightspeed);
    	//	rmotor1.set(rightspeed);
    	//	rmotor2.set(rightspeed);
    		lmotor0.set(leftspeed);
    	//	lmotor1.set(leftspeed);
    	//	lmotor2.set(leftspeed);
    	
    	}
    	
    }
    
    public double getrSpeed(){
    	
    	return 0.0;		//unit is ft/s to make intuitive
    	
    }
    
    public double getlSpeed(){
    	
    	return 0.0;		//unit is ft/s to make intuitive
    	
    }

}

