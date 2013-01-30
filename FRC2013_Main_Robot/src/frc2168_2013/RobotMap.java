package frc2168_2013;

import edu.wpi.first.wpilibj.Talon;

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
	
	//TODO: organize the rest of these. Some should live in OI, not RobotMap
	public static final boolean rInvert = false;
	public static final boolean lInvert = true;
		
	public static final int rightJoyAxis = 5;
	public static final int leftJoyAxis = 2;
	
	public static final int baseDriveJoystick = 1;
	public static final int operatorDriveJoystick = 2;
	
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
	 *                       Shooter Parameters                     *
	 ****************************************************************/
	//Shooter Wheel Encoder///////////////////////////////////////////////
	//TODO: Define the distance per tick, and the encoder rate
	public static final double shooterEncoderDistPerTick = 0.0; //units in inches
	public static final double shooterEncoderMinRate = 0.0;
	public static final boolean shooterEncoderReverse = false;
	

	/****************************************************************
	 *                       Drive Train Parameters                 *
	 ****************************************************************/
	//Wheel Radius
	public static final int driveWheelRadius=2;//Colson wheel radius in inches

	//average encoder
	public static final int driveAvgEncoderVal = 10;

	//DriveTraincEncoder Parameters
	public static final int pulsePerRotation = 360; //encoder ticks per rotation
	public static final int gearRatio = 1/1; //ratio between wheel and encoder
	public static final int driveEncoderPulsePerRot= pulsePerRotation*gearRatio; //pulse per rotation * gear ratio
	public static final double driveEencoderDistPerTick=(Math.PI*2*driveWheelRadius)/driveEncoderPulsePerRot;
	public static final int driveEncoderMinRate=10; 
	public static final int driveEncoderMinPeriod=10;
	public static final boolean leftDriveTrainEncoderReverse=false;
	public static final boolean rightDriveTrainEncoderReverse=false;

	//PID Parameters//////////////////////////////////////////////
	//drivetrain controller steady state determination
	public static final int drivetrainArraySize = 50;
	public static final double drivetrainPercent = 0.1;

	//period to run PID loops
	public static final long driveTrainPIDPeriod = 100;//100ms loop

	//PID Gains for Left & Right Speed and Position
	//Bandwidth =
	//Phase Margin = 
	public static final double driveTrainLeftSpeedP = 0.00574562908722711;
	public static final double driveTrainLeftSpeedI = 0.000308064641742337; 
	public static final double driveTrainLeftSpeedD = -0.000130778888124088;
	
	public static final double driveTrainRightSpeedP = 0.00574562908722711;
	public static final double driveTrainRightSpeedI = 0.000308064641742337; 
	public static final double driveTrainRightSpeedD = -0.000130778888124088;
	
	public static final double driveTrainLeftPositionP = 0.00574562908722711;
	public static final double driveTrainLeftPositionI = 0.000308064641742337; 
	public static final double driveTrainLeftPositionD = -0.000130778888124088;
	
	public static final double driveTrainRightPositionP = 0.00574562908722711;
	public static final double driveTrainRightPositionI = 0.000308064641742337; 
	public static final double driveTrainRightPositionD = -0.000130778888124088;
	
}
