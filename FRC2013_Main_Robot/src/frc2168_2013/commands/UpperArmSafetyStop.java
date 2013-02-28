package frc2168_2013.commands;

/**
 * A command to disengage the hanger mechanism.
 * 
 * @author ICW
 *
 */
public class UpperArmSafetyStop extends CommandBase {

	public UpperArmSafetyStop() {
		requires (arm);
	}

	protected void initialize() {
		//Nothing to do here
	}

	protected void execute() {
		if(arm.highHardStopPressed()){
			arm.setArmPWM(0.0);//if the top limit switch is pressed, stop the arm motor from command out stuff
		}else{
			arm.setArmPWM(oi.getoperatorDriveRightStick() * 0.7);//else, continue driving the motor
		}
	}

	protected void interrupted() {
		//Nothing to do here
	}

	protected void end() {
		//Nothing to do here
	}

	protected boolean isFinished() {
		return true;	//im not sure if it is supposed to return true or false
	}
}
