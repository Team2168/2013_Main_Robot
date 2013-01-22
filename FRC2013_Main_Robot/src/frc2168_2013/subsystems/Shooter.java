package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.commands.SetShooterSpeedPWM;

public class Shooter extends Subsystem {
	
	/**
	 * Set the dafault command for this subsystem.
	 */
    public void initDefaultCommand() {
    	//Set the shooter wheels to not spin.
    	setDefaultCommand(new SetShooterSpeedPWM(0.0, 0.0));
    }
    
    /**
     * Set the speeds of the shooter wheels. Where wheel1 is the first wheel
     * the disc will hit when shot, and wheel2 is the second.
     * 
     * @param wheel1 Speed for wheel 1 in RPM
     * @param wheel2 Speed for wheel 2 in RPM
     */
    public void setSpeed(double speed1, double speed2){
    	//TODO: Write code for this method
    }
    
    /**
     * Set the speeds of the shooter wheels. Where wheel1 is the first wheel
     * the disc will hit when shot, and wheel2 is the second.
     * 
     * @param wheel1 Speed for wheel 1 (1.0 to -1.0)
     * @param wheel2 Speed for wheel 2 (1.0 to -1.0)
     */
    public void setPWM(double speed1, double speed2){
    	//TODO: Write code for this method
    }
    
    /**
     * Get the speed of the shooter in RPM.
     * 
     * @return the speed of the shooter (RPM)
     */
    public double getSpeed(){
    	//TODO: Write code for this method
    	return 0.0;
    }
}

