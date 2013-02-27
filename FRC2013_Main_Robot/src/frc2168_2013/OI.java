
package frc2168_2013;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button; 
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc2168_2013.utils.JoystickAnalogButton;
import frc2168_2013.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static final boolean      rInvert = true;  //for R driveTrain
	public static final boolean      lInvert = false; //for L driveTrain
	public static final boolean      ainvert = true;  //for arm left motor
	public static final boolean      sInvert = true;  //for shooter
	public static final boolean      hInvert = true;  //for hopper
	public static final int     rightJoyAxis = 5;
	public static final int      leftJoyAxis = 2;
	public static final int      triggerAxis = 3;
	
	// minSpeed needs to be tweaked based on the particular drivetrain.
	// It is the speed at which the drivetrain barely starts moving
	private static final double minDriveSpeed = 0.125;
	static double joystickScale[][] = {
		/* Joystick Input, Scaled Output */
		{ 1.00, 1.00 },
		{ 0.90, 0.68 },
		{ 0.50, 0.32 },
		{ 0.06, minDriveSpeed },
		{ 0.06, 0.00 },
		{ 0.00, 0.00 },
		{ -0.06, 0.00 },
		{ -0.06, -minDriveSpeed },
		{ -0.50, -0.32 },
		{ -0.90, -0.68 },
		{ -1.00, -1.00 } };
	
	
	
	///////////////////////////////////////////////////////////////////////////
	//  Driver Joystick                                                      //
	///////////////////////////////////////////////////////////////////////////

	//Create variable for USB joystick
	public static final int baseDriveJoystick = 1;
	public Joystick baseDriver = new Joystick(baseDriveJoystick);
	
	//Create mapping for buttons on joystick
	public Button driveButtonA = new JoystickButton(baseDriver, 1),
				  driveButtonB = new JoystickButton(baseDriver, 2),
				  driveButtonX = new JoystickButton(baseDriver, 3),
				  driveButtonY = new JoystickButton(baseDriver, 4),
				  driveButtonLeftBumper = new JoystickButton(baseDriver, 5),
				  driveButtonRightBumper = new JoystickButton(baseDriver, 6),
				  driveButtonReset = new JoystickButton(baseDriver, 7),
				  driveButtonStart = new JoystickButton(baseDriver, 8);

	/**
	 * Get the adjusted left joystick value
	 * 
	 * @return The driver's left joystick value
	 */
	public double getbaseDriverLeftAxis() {
		double leftSpeed = interpolate(baseDriver.getRawAxis(leftJoyAxis));
		
		//If the trigger (brake) is pressed, use falcon claw
		if (baseDriver.getRawAxis(3) < -0.01) {
			leftSpeed = falconClaw(leftSpeed, baseDriver.getRawAxis(triggerAxis));
		}
		
		//flip sign so up on stick is a positive value (forward motion)
		return -leftSpeed;
	}
	
	/**
	 * Get the adjusted right joystick value
	 * 
	 * @return The driver's right joystick value
	 */
	public double getbaseDriverRightAxis() {
		double rightSpeed = interpolate(baseDriver.getRawAxis(rightJoyAxis));
		
		//If the trigger (brake) is pressed, use falcon claw
		if (baseDriver.getRawAxis(3) < -0.01) {
			rightSpeed = falconClaw(rightSpeed, baseDriver.getRawAxis(triggerAxis));
		}
		
		//flip sign so up on stick is a positive value (forward motion)
		return -rightSpeed;
	}
	
	/**
	 * Electronic braking - aka "Falcon Claw"
	 * The more the "brake" is pulled, the slower output speed
	 * 
	 * @param inputSpeed The input value to scale back based on brake input. (1 to -1)
	 * @param brake The brake input value. (0 to -1)
	 * @return The adjusted value.
	 */
	private double falconClaw(double inputSpeed, double brake) {
		return ((1 - ((-minDriveSpeed + 1) * Math.abs(brake))) * inputSpeed);
	}

	/**
	 * A function to modify the joystick values using linear interpolation.
	 * The objective is augment the joystick value going to the motor controllers
	 *   to widen the region of "fine" control while still allowing full speed.
	 * 
	 * @param input The value to augment.
	 * @return The adjusted value.
	 */
	private double interpolate(double input) {
		double retVal = 0.0;
		boolean done = false;
		double m, b;
		
		//make sure input is between 1.0 and -1.0
		if (input > 1.0) {
			input = 1.0;
		} else if (input < -1.0) {
			input = -1.0;
		}
		
		//Find the two points in our array, between which the input falls. 
		//We will start at i = 1 since we can't have a point fall outside our array.
		for (int i = 1; !done && i < joystickScale.length; i++) {
			if (input >= joystickScale[i][0]) {
				//We found where the point falls in out array, between index i and i-1
				//Calculate the equation for the line. y=mx+b
				m = (joystickScale[i][1] - joystickScale[i-1][1])/(joystickScale[i][0] - joystickScale[i-1][0]);
				b = joystickScale[i][1] - (m * joystickScale[i][0]);
				retVal = m * input + b;
				
				//we're finished, don't continue to loop
				done = true;
			}
		}
		
		return retVal;
	}



	///////////////////////////////////////////////////////////////////////////
	//  Operator Joystick                                                    //
	///////////////////////////////////////////////////////////////////////////
	public static final int operatorDriveJoystick = 2;
	public Joystick operatorDrive = new Joystick(operatorDriveJoystick);
	
	public Button operatorButtonA = new JoystickButton(operatorDrive, 1),
				  operatorButtonB = new JoystickButton(operatorDrive, 2),
				  operatorButtonX = new JoystickButton(operatorDrive, 3),
				  operatorButtonY = new JoystickButton(operatorDrive, 4),
				  operatorButtonLeftBumper = new JoystickButton(operatorDrive, 5),
				  operatorButtonRightBumper = new JoystickButton(operatorDrive, 6),
				  operatorButtonReset = new JoystickButton(operatorDrive, 7),
				  operatorButtonStart = new JoystickButton(operatorDrive, 8);
	public JoystickAnalogButton operatorTriggerR = new JoystickAnalogButton(operatorDrive, 3, -0.5),
								operatorTriggerL = new JoystickAnalogButton(operatorDrive, 3, 0.5),
								operatorDPadL = new JoystickAnalogButton(operatorDrive, 6, -0.5),	
								operatorDPadR = new JoystickAnalogButton(operatorDrive, 6, 0.5);
	
	public double getoperatorDriveLeftStick() {
		return -operatorDrive.getRawAxis(leftJoyAxis);
	}
	
	public double getoperatorDriveRightStick() {
		return -operatorDrive.getRawAxis(rightJoyAxis); 
	}
	
	public double getoperatorTrigger(){
		return operatorDrive.getRawAxis(3);
	}
	
	
	public OI() {
		//DRIVER BUTTON MAP//
		driveButtonLeftBumper.whileHeld(new HangerDisengage()); //disengage the hanger
		driveButtonRightBumper.whileHeld(new HangerEngage()); //engage the hanger

		//OPERATOR BUTTON MAP//
//		operatorTriggerR.whenPressed(new ShootSingleFrisbee()); //shoot one disc
//		operatorTriggerL.whenPressed(new ShootSingleFrisbee()); //shoot one disc
		operatorTriggerR.whileHeld(new DriveHopperJoystick(0.9));
		operatorTriggerL.whileHeld(new DriveHopperJoystick(-0.9));
		
		operatorButtonLeftBumper.whenPressed(new StopperDisengage());
		operatorButtonRightBumper.whenPressed(new StopperEngage());
		
		operatorButtonA.whenPressed(new ArmPIDPause());
		operatorButtonB.whenPressed(new ArmPIDPosition(82));
		
		operatorButtonX.whenPressed(new ArmPIDPosition());
//		operatorButtonY.whenPressed(new ArmPIDPosition());

	}
	
	
	//// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

