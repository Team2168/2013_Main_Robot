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
	public static final int leftDriveMotor = 1;
	public static final int rightDriveMotor = 2;
	public static final int shooterMotor = 3;
	public static final int hopperMotor = 4;
	
	//DIO Channels////////////////////////////////////////////////////
	public static final int leftDriveEncoderChannelA = 1;
	public static final int leftDriveEncoderChannelB = 2;
	public static final int rightDriveEncoderChannelA = 3;
	public static final int rightDriveEncoderChannelB = 4;
	public static final int shooterEncoderChannelA = 5;
	public static final int shooterEncoderChannelB = 6;
	public static final int hopperDisc1 = 7;
	public static final int hopperDisc2 = 8;
	public static final int hopperDisc3 = 9;
	public static final int hopperDisc4 = 10;
	public static final int compressorPressureSwitch = 14;

	//Analog Input Channels///////////////////////////////////////////
	
	//Relay Output Channels///////////////////////////////////////////
	public static final int compressorPower = 1;
	
	//Solenoid Channels///////////////////////////////////////////////
	public static final int hopperDiscStopperApply = 1;
	public static final int hopperDiscStopperRelease = 2;
	
	
	/****************************************************************
	 *                       Encoder Parameters                     *
	 ****************************************************************/
	//Shooter Wheel Encoder///////////////////////////////////////////////
	//TODO: Define the distance per tick, and the encoder rate
	public static final double shooterEncoderDistPerTick = 0.0; //units in inches
	public static final double shooterEncoderMinRate = 0.0;
	public static final boolean shooterEncoderReverse = false;
	
	
	
	
	
	
	
	//TODO: organize the rest of these. Some should live in OI, not RobotMap
	public static final boolean rInvert = false;
	public static final boolean lInvert = true;
	
	
	public static final int rightJoyAxis = 5;
	public static final int leftJoyAxis = 2;
	
	public static final int baseDriveJoystick = 1;
	public static final int operatorDriveJoystick = 2;
}
