package frc2168_2013.commands;

public class SetIntakeSpeedPWM extends CommandBase {
	double lowerRollerSpeed, upperRollerSpeed;
	
	/**
	 * The default constructor for this command.
	 * 
	 * @param speed1 the PWM speed for the upper roller.
	 * @param speed2 the PWM speed for the lower roller.
	 */
	public SetIntakeSpeedPWM (double upperRollerSpeed,
			double lowerRollerSpeed) {
		requires(intake);
		this.lowerRollerSpeed = lowerRollerSpeed;
		this.upperRollerSpeed = upperRollerSpeed;
	}
	
	
	protected void initialize() {
		//Set the "speed" for the wheels as provided in the constructor
		intake.setPWM(upperRollerSpeed, lowerRollerSpeed);
	}

	
	protected void execute() {
		//Nothing to do here
	}

	
	protected boolean isFinished() {
		return false; //The default command never completes.
	}

	
	protected void end() {
		//Notihng to do here
	}

	
	protected void interrupted() {

	}

}
