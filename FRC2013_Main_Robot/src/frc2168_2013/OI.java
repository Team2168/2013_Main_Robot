
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
    
	///////////////////////////////////////////////////////////////////////////
	//  Driver Joystick
	///////////////////////////////////////////////////////////////////////////
	//Create variable for USB joystick
	public Joystick baseDriver = new Joystick(RobotMap.baseDriveJoystick);
	
	
	public static final boolean sInvert = false; //for arm
	public static final boolean hInvert = false; //for hopper
	
	//Create mapping for buttons on joystick
	//Driver Joystick
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
		if (baseDriver.getRawAxis(3) < 0) { // Use electronic braking
				return ((((-RobotMap.mod + 1) * baseDriver.getRawAxis(3)) + 1) * baseDriver.getRawAxis(2));
		} else {
				return baseDriver.getRawAxis(1);
		}
	}
	
	public double getbaseDriverRightAxis() {
		if (baseDriver.getRawAxis(3) < 0) { // Use electronic braking
				return ((((-RobotMap.mod + 1) * baseDriver.getRawAxis(3)) + 1) * baseDriver.getRawAxis(5));
		} else {
				return baseDriver.getRawAxis(4); 
		}
	}
	
	
	
	
	///////////////////////////////////////////////////////////////////////////
	//  Operator Joystick
	///////////////////////////////////////////////////////////////////////////
	public Joystick operatorDrive = new Joystick(RobotMap.operatorDriveJoystick);
	public Button   operatorButtonA = new JoystickButton(operatorDrive, 1),
					operatorButtonB = new JoystickButton(operatorDrive, 2),
					operatorButtonX = new JoystickButton(operatorDrive, 3),
					operatorButtonY = new JoystickButton(operatorDrive, 4),
					operatorButtonLeftBumper = new JoystickButton(operatorDrive, 5),
					operatorButtonRightBumper = new JoystickButton(operatorDrive, 6),
					operatorButtonReset = new JoystickButton(operatorDrive, 7),
					operatorButtonStart = new JoystickButton(operatorDrive, 8);
	public JoystickAnalogButton auxTriggerR = new JoystickAnalogButton(operatorDrive, 3, -0.5),
								auxTriggerL = new JoystickAnalogButton(operatorDrive, 3, 0.5),
								auxDPadL = new JoystickAnalogButton(operatorDrive, 6, -0.5),	
								auxDPadR = new JoystickAnalogButton(operatorDrive, 6, 0.5);
	
	public double getoperatorDriveLeftStick() {
			return operatorDrive.getRawAxis(2);
	}
	
	public double getoperatorDriveRightStick() {
			return operatorDrive.getRawAxis(5); 
	}
	
	
	public OI() {
		//Map buttons to commands (operator and driver)
	//	driveButtonLeftBumper.whenPressed(); //disengage the hanger
	//	driveButtonRightBumper.whenPressed(); //engage the hanger
	//	operatorButtonA.whenPressed(); //shooter on
		//delete me
		operatorButtonB.whenPressed(new StopShooterWheel()); //shooter off 
		auxTriggerR.whenPressed(new ShootSingleFrisbee()); //shoot one disc
		auxTriggerL.whenPressed(new ShootSingleFrisbee()); //shoot one disc
		
		driveButtonA.whenPressed(new DrivePIDPause());
		driveButtonB.whenPressed(new DrivePIDPosition(360));
		driveButtonX.whenPressed(new DrivePIDSpeed(300));
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

