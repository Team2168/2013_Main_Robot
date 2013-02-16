
package frc2168_2013;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button; 
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
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
	public static final boolean aInvert = false; //for arm
	public static final boolean sInvert = false; //for shooter
	public static final boolean hInvert = false; //for hopper
	public static final int rightJoyAxis = 5;
	public static final int leftJoyAxis = 2;
	
	
	///////////////////////////////////////////////////////////////////////////
	//  Driver Joystick                                                      //
	///////////////////////////////////////////////////////////////////////////
	//Falcon Claw Brake Modifier
	public static final double mod = 0.125;	// Low minimum/modifier for Falcon Claw
	
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
	
	//Convenience functions for joystick axis'
	public double getbaseDriverLeftAxis() {
		if (baseDriver.getRawAxis(3) < -0.01) {
			// Use electronic braking - Falcon Claw
			//The more the triggers are pulled, less voltage goes to the
			//  drivetrain motors.
			return ((((-mod + 1) * baseDriver.getRawAxis(3)) + 1)
					* baseDriver.getRawAxis(leftJoyAxis));
		} else {
			//otherwise 
			return -baseDriver.getRawAxis(leftJoyAxis);
		}
	}
	
	public double getbaseDriverRightAxis() {
		if (baseDriver.getRawAxis(3) < -0.01) {
			// Use electronic braking - Falcon Claw
			//The more the triggers are pulled, less voltage goes to the
			//  drivetrain motors. 
			return ((((-mod + 1) * baseDriver.getRawAxis(3)) + 1)
					* baseDriver.getRawAxis(rightJoyAxis));
		} else {
			return -baseDriver.getRawAxis(rightJoyAxis); 
		}
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
	
	
	public OI() {
		//Map buttons to commands (operator and driver)
		//driveButtonLeftBumper.whenPressed(); //disengage the hanger
		//driveButtonRightBumper.whenPressed(); //engage the hanger
		//operatorButtonA.whenPressed(); //shooter on
		//delete me
		operatorButtonB.whenPressed(new StopShooterWheel()); //shooter off 
		operatorTriggerR.whenPressed(new ShootSingleFrisbee()); //shoot one disc
		operatorTriggerL.whenPressed(new ShootSingleFrisbee()); //shoot one disc
		
		driveButtonA.whenPressed(new DrivePIDPause());
		driveButtonB.whenPressed(new DrivePIDPosition());
		driveButtonX.whenPressed(new DrivePIDSpeed());
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

