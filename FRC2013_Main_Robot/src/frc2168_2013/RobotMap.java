package frc2168_2013;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	/****************************************************************
	 *                       CRIO MAP                               *
	 ****************************************************************/
	
	//PWM Channels////////////////////////////////////////////////////
	public static final int leftMotor = 1;
	public static final int rightMotor = 2;
	
	
	//TODO: organize the rest of these. Some should live in OI, not RobotMap
	public static final boolean rInvert = false;
	public static final boolean lInvert = true;
	
	
	public static final int rightJoyAxis = 5;
	public static final int leftJoyAxis = 2;
	
	public static final int baseDriveJoystick = 1;
	public static final int operatorDriveJoystick = 2;
}
