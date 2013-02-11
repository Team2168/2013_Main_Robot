package frc2168_2013;

import edu.wpi.first.wpilibj.CounterBase;
import frc2168_2013.PIDController.Sensors.AverageEncoder;

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
		
	public static final int rightJoyAxis = 6;
	public static final int leftJoyAxis = 2;
	
	public static final int baseDriveJoystick = 1;
	public static final int operatorDriveJoystick = 2;
	
	//PWM Channels////////////////////////////////////////////////////
	public static final int leftDriveMotor = 1;
	public static final int rightDriveMotor = 2;
	public static final int shooterMotor = 3;
	public static final int hopperMotor = 4;
	public static final int armMotor =5;
	
	//DIO Channels////////////////////////////////////////////////////
	public static final int leftDriveEncoderChannelA = 1;
	public static final int leftDriveEncoderChannelB = 2;
	public static final int rightDriveEncoderChannelA = 3;
	public static final int rightDriveEncoderChannelB = 4;
	public static final int shooterEncoderChannelA = 5;
	public static final int shooterEncoderChannelB = 6;
	public static final int armEncoderChannelA = 7;
	public static final int armEncoderChannelB = 8;
	public static final int hopperDisc1 = 9;
	public static final int hopperDisc2 = 10;
	public static final int hopperDisc3 = 11;
	public static final int hopperDisc4 = 12;

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
	//arm Radius
	public static final int shooterWheelRadius=3;//Colson wheel radius in inches
	
	private static final int shooterPulsePerRotation = 256; //encoder ticks per rotation (LEAVE PRIVATE)
	private static final double shooterGearRatio = 30.0/24.0; //ratio between wheel over encoder (LEAVE PRIVATE)
	public static final int shooterEncoderPulsePerRot=(int) (shooterPulsePerRotation*shooterGearRatio); //pulse per rotation * gear ratio
	public static final double shooterEncoderDistPerTick=(Math.PI*2*shooterWheelRadius)/shooterEncoderPulsePerRot;
	public static final CounterBase.EncodingType shooterEncodingType= CounterBase.EncodingType.k4X; //count rising and falling edges on both channels
	public static final AverageEncoder.PositionReturnType shooterPosReturnType = AverageEncoder.PositionReturnType.DEGREE;
	public static final AverageEncoder.SpeedReturnType shooterSpeedReturnType = AverageEncoder.SpeedReturnType.RPM;
	public static final int shooterEncoderMinRate=10; 
	public static final int shooterEncoderMinPeriod=10;
	public static final boolean shooterEncoderReverse=false;
	public static final int shooterAvgEncoderVal = 1;
		
	
	//period to run PID loops on shooter
	public static final long shooterPIDPeriod = 100;//100ms loop
	
	//PID Gains for Shooter Wheel Speed Controller
	public static final double shooterSpeedP = 0.00574562908722711;
	public static final double shooterSpeedI = 0.000308064641742337; 
	public static final double shooterSpeedD = -0.000130778888124088;

	
	/****************************************************************
	 *                       Arm Parameters                     *
	 ****************************************************************/
	//arm Encoder///////////////////////////////////////////////
	//arm Radius
	public static final int armWheelRadius=3;//Colson wheel radius in inches
	
	//Drivetrain Encoder Parameters /////////////////////////////////
	private static final int armPulsePerRotation = 256; //encoder ticks per rotation (LEAVE PRIVATE)
	private static final double armGearRatio = 30.0/24.0; //ratio between wheel over encoder (LEAVE PRIVATE)
	public static final int armEncoderPulsePerRot=(int) (armPulsePerRotation*armGearRatio); //pulse per rotation * gear ratio
	public static final double armEncoderDistPerTick=(Math.PI*2*armWheelRadius)/armEncoderPulsePerRot;
	public static final CounterBase.EncodingType armEncodingType= CounterBase.EncodingType.k4X; //count rising and falling edges on both channels
	public static final AverageEncoder.PositionReturnType armPosReturnType = AverageEncoder.PositionReturnType.DEGREE;
	public static final AverageEncoder.SpeedReturnType armSpeedReturnType = AverageEncoder.SpeedReturnType.RPM;
	public static final int armEncoderMinRate=10; 
	public static final int armEncoderMinPeriod=10;
	public static final boolean armEncoderReverse=false;
	public static final int armAvgEncoderVal = 1;

	
	//arm voltage constant
	public static final double hopperVoltage = 0.4;

	
	//period to run PID loops on arm
	public static final long armPIDPeriod = 100;//100ms loop
	
	//PID Gains for Arm Position Controller
	public static final double armPosP = 0.00574562908722711;
	public static final double armPosI = 0.000308064641742337; 
	public static final double armPosD = -0.000130778888124088;
	
	/****************************************************************
	 *                       Drive Train Parameters                 *
	 ****************************************************************/
	//Wheel Radius
	public static final int driveWheelRadius=2;//Colson wheel radius in inches

	
	//Drivetrain Encoder Parameters /////////////////////////////////
	private static final int drivePulsePerRotation = 360; //encoder ticks per rotation
	private static final double driveGearRatio = 30.0/24.0; //ratio between wheel over encoder
	public static final int driveEncoderPulsePerRot=(int) (drivePulsePerRotation*driveGearRatio); //pulse per rotation * gear ratio
	public static final double driveEncoderDistPerTick=(Math.PI*2*driveWheelRadius)/driveEncoderPulsePerRot;
	public static final CounterBase.EncodingType driveEncodingType= CounterBase.EncodingType.k4X; //count rising and falling edges on both channels
	public static final AverageEncoder.PositionReturnType drivePosReturnType = AverageEncoder.PositionReturnType.DEGREE;
	public static final AverageEncoder.SpeedReturnType driveSpeedReturnType = AverageEncoder.SpeedReturnType.RPM;
	public static final int driveEncoderMinRate=10; 
	public static final int driveEncoderMinPeriod=10;
	public static final boolean leftDriveTrainEncoderReverse=true;
	public static final boolean rightDriveTrainEncoderReverse=false;
	public static final int driveAvgEncoderVal = 5;

	//PID Parameters //////////////////////////////////////////////////
	public static final int drivetrainArraySize = 50;
	public static final double drivetrainPercent = 0.1;

	//period to run PID loops on drive train
	public static final long driveTrainPIDPeriod = 100;//100ms loop

	//PID Gains for Left & Right Speed and Position
	//Bandwidth =
	//Phase Margin = 
	public static final double driveTrainLeftSpeedP = 0.00574562908722711;
	public static final double driveTrainLeftSpeedI = 0.000308064641742337; 
	public static final double driveTrainLeftSpeedD = -0.000130778888124088;
	
	public static final double driveTrainRightSpeedP = 0.002;
	public static final double driveTrainRightSpeedI = 0.0001412646174233;  
	public static final double driveTrainRightSpeedD = 0.0074778888124088;
	
	public static final double driveTrainLeftPositionP = 0.002;
	public static final double driveTrainLeftPositionI = 0.0001412646174233;  
	public static final double driveTrainLeftPositionD = 0.0074778888124088;
	
	public static final double driveTrainRightPositionP = 0.00574562908722711;
	public static final double driveTrainRightPositionI = 0.000308064641742337; 
	public static final double driveTrainRightPositionD = -0.000130778888124088;
	
	public static int mod;
	public static int driverJoystick;
	

	/****************************************************************
	 *                TCP Servers  (ONLY FOR DEBUGGING)             *
	 ****************************************************************/
	public static final int TCPServerRightDrivetrainPos = 1180;
	public static final int TCPServerRightDrivetrainSpeed = 1181;
	public static final int TCPServerLeftDrivetrainPos = 1182;
	public static final int TCPServerLeftDrivetrainSpeed = 1183;
	public static final int TCPServerShooterSpeed = 1184;
	public static final int TCPServerArmPos = 1185;
	
}
