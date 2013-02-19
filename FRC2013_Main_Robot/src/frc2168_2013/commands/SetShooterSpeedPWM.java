package frc2168_2013.commands;

public class SetShooterSpeedPWM extends CommandBase {

	double speed;
	
	/**
	 * The default constructor for this command.
	 * 
	 * @param speed the PWM speed for the shooter wheel.
	 */
	public SetShooterSpeedPWM (double speed) {
		requires(shooter);
		this.speed = speed;
	}
	
	protected void initialize() {
		//Set the "speed" for the wheels as provided in the constructor
		shooter.setShooterPWM(speed);
	}

	protected void execute() {
		//Nothing to do here
	}

	protected boolean isFinished() {
		return false; //The default command never completes
	}

	protected void end() {
		//Nothing to do here
	}

	protected void interrupted() {
		//Nothing to do here
	}

}
