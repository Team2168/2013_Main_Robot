package frc2168_2013.commands;

/**
 * A command to disengage the hanger mechanism.
 * 
 * @author ICW
 *
 */
public class DriveArmDownTillLowerLimitSwitch extends CommandBase {

	public DriveArmDownTillLowerLimitSwitch() {
		requires (arm);
	}

	protected void initialize() {
		//Nothing to do here
	}

	protected void execute() {
		if(arm.lowHardStopPressed()){
			arm.setArmPWM(0.0);//set motor voltage to 0.0 when the lower limit switch is pressed
			arm.armPosController.reset();	//I couldnt call the encoder in the command so i think reseting the controller will reset it
		}else{
			arm.setArmPWM(-.5);
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
