package frc2168_2013.commands;

public class SetShooterSpeedPWM extends CommandBase {

	double speed1, speed2;
	
	/**
	 * The default constructor for this command.
	 * 
	 * @param speed1 the PWM speed for the first wheel the disc will hit when
	 *   shot.
	 * @param speed2 the PWM speed for the second wheel the disc will hit when
	 *   shot.
	 */
	public SetShooterSpeedPWM (double speed1, double speed2) {
		requires(shooter);
		this.speed1 = speed1;
		this.speed2 = speed2;
	}
	
	protected void initialize() {
		//Set the "speed" for the wheels as provided in the constructor
		shooter.setPWM(speed1, speed2);
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
