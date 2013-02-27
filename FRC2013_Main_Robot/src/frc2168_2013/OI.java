
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
	public static final boolean rInvert = true;  //for R driveTrain
	public static final boolean lInvert = false; //for L driveTrain
	public static final boolean ainvert = true; //for arm left motor
	public static final boolean sInvert = true; //for shooter
	public static final boolean hInvert = true; //for hopper
	public static final int rightJoyAxis = 5;
	public static final int leftJoyAxis = 2;
	public static final int triggerAxis = 3;
	
	
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
		if (baseDriver.getRawAxis(3) < -0.01) {
			//If the trigger (brake) is pressed, use falcon claw
			return falconClaw(baseDriver.getRawAxis(rightJoyAxis),
					baseDriver.getRawAxis(triggerAxis));
		} else {
			//otherwise just return the inverted stick value
			return -baseDriver.getRawAxis(leftJoyAxis);
		}
	}
	
	/**
	 * Get the adjusted right joystick value
	 * 
	 * @return The driver's right joystick value
	 */
	public double getbaseDriverRightAxis() {
		if (baseDriver.getRawAxis(3) < -0.01) {
			//If the trigger (brake) is pressed, use falcon claw
			return falconClaw(baseDriver.getRawAxis(rightJoyAxis),
					baseDriver.getRawAxis(triggerAxis));
		} else {
			//otherwise just return the inverted stick value
			return -baseDriver.getRawAxis(rightJoyAxis); 
		}
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
		// minSpeed needs to be tweaked based on the particular drivetrain.
		// It is the speed to travel at when drive sticks are full up, and the
		//   "brake" is fully applied. 
		// e.g. The speed at which the drivetrain barely starts moving
		final double minSpeed = 0.125;
		
		return ((1 - ((-minSpeed + 1) * Math.abs(brake))) * inputSpeed);
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

