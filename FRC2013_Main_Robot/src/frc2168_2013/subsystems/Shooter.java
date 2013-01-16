package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	
	double speed1 = 69;
	double speed2 = 9001;
	
    public void initDefaultCommand() {
    }
    
    public void setSpeed(double speed1, double speed2){
    	
    }
    public double getSpeed(){
    	return speed1 + speed2;
    }
}

