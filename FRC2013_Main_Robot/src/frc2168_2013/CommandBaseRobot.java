/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc2168_2013;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc2168_2013.commands.CommandBase;
import frc2168_2013.commands.Auto.*;
import frc2168_2013.dashboard.CompetitionDashboard;
import frc2168_2013.utils.BitRelay;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class CommandBaseRobot extends IterativeRobot {

    Command autonomousCommand;
    Command armPositionInit;
    Command dashboard;

    Compressor compressor;
    
    SendableChooser autoChooser;
    SendableChooser dashChooser;
    
    //These variables are for the serial communication with the arduino.
    private static boolean shooterAtSpeed = false,
                   discFired = false,
                   againstBar = false,
                   endGame = false,
                   autoMode = false;
    
    private static int numberOfDiscs = 3;    //TODO: change this to actually use a sensor
    
    BitRelay lightsRelay1,
          lightsRelay2,
          lightsRelay3,
          lightsRelay4;
    
    /**
     * This method is run when the robot is first started up and should be
     * used for any initialization code.
     */
	public void robotInit() {
        // Initialize all subsystems
        CommandBase.init();
        
        //Start the compressor
        compressor = new Compressor(RobotMap.compressorPressureSwitch, RobotMap.compressorPower);

    	//armPositionInit.start();
        compressor.start();
        
        
        //Initialize auto mode chooser
        autoSelectInit();
        
        //Initialize dashboard
        dashSelectInit();
        
        //Initialize serial communication for arduino
        //SerialCommunicator.init(57600, 8, SerialPort.Parity.kNone, SerialPort.StopBits.kOne);
        
        //Initialize relay ports for light strip states
        lightsRelay1 = new BitRelay(RobotMap.arduinoRelay1);
        lightsRelay2 = new BitRelay(RobotMap.arduinoRelay2);
        lightsRelay3 = new BitRelay(RobotMap.arduinoRelay3);
        lightsRelay4 = new BitRelay(RobotMap.arduinoRelay4);
        
        //End of Robot Init
    	System.out.println("ROBOT FINISHED LOADING!");
    }

    /**
     * This method is called once, when the robot first enters auto mode.
     */
    
	public void autonomousInit() {
    	Scheduler.getInstance().enable();
    	
    	// instantiate the command used for the autonomous period
        autonomousCommand = (Command) autoChooser.getSelected();
        //autonomousCommand = new RearOfPyramid_3pt_Side();
    	// schedule the autonomous command (example)
        autonomousCommand.start();
        
        
        //start dashboard
        dashboard = (Command) dashChooser.getSelected();
        dashboard.start();
        
        //send auto mode bit for arduino
        setAutoMode(true);
        setArduinoStatus();
    }

    /**
     * This method is called periodically during autonomous
     */
    
	public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
        setArduinoStatus();
    }

    /**
     * 
     * This method is called once, when the robot first enters teleop mode.
     */
    
	public void teleopInit() {
    	Scheduler.getInstance().enable();
    	// This makes sure that the autonomous stops running when teleop starts
    	// running. If you want the autonomous to continue until interrupted by
    	// another command, remove this line or comment it out.
    	if(autonomousCommand != null)
    		autonomousCommand.cancel();
    	
        //start dashboard
        dashboard = (Command) dashChooser.getSelected();
        dashboard.start();
        
        setAutoMode(false);
        setArduinoStatus();
    }

    /**
     * This method is called periodically during operator control
     */
    
	public void teleopPeriodic() {
    	Scheduler.getInstance().run();
    	
    	if(DriverStation.getInstance().getMatchTime() >= 115){
    		setEndGame(true);
    	}
    	setArduinoStatus();
    }
    
    /**
     * This method is called periodically during test mode
     */
    
	public void testPeriodic() {
        LiveWindow.run();
    }
    
    /**
     * This method is called once, the first time the robot gets disabled. 
     */
    
	public void disabledInit() {
    	//TODO: automatically deploy lifter if it isn't already? last minute hang

    	//Kill all active commands and make sure new ones don't run.
    	Scheduler.getInstance().removeAll();
    	Scheduler.getInstance().disable();
    	
    	//SerialCommunicator.free();
    }
    
    /**
     * This method gets called repeatedly while the robot is disabled.
     */
    
	public void disabledPeriodic() {
    }
    
    private void autoSelectInit() {
        autoChooser = new SendableChooser();
        
        autoChooser.addDefault("3 disc far Auto - Sides", new RearOfPyramid_3pt_Side(5,0.5,0.5));
        autoChooser.addObject("3 disc far Auto - Center", new RearOfPyramid_3pt_Center(5,0.5,0.5));
        //autoChooser.addObject("2 disc close Auto - Center", new FrontOfPyramid_3pt_Center());
        //autoChooser.addObject ("2 disc close Auto - Right", new FrontOfPyramid_3pt_Right());
        //autoChooser.addObject ("2 disc close Auto - Left", new FrontOfPyramid_3pt_Left());
        
        SmartDashboard.putData("Autonomous mode", autoChooser);
    }
    
    private void dashSelectInit() {
        dashChooser = new SendableChooser();
        dashChooser.addDefault("Competition Dash", new CompetitionDashboard());
        //dashChooser.addObject("PID Debug Dash", new ShooterWheelPIDDashboard());
        SmartDashboard.putData("Dashboard Chooser", dashChooser);
    }
    
    /**
     * This method handles outputting the data to the arduino for lighting
     */
    void setArduinoStatus() {
    	// COMMUNICATION PROTOCOL - BITMAP
    	// BIT(S)     Meaning
    	// ------------------------------
    	// 0 - 2      # discs (0 - 4)
    	//   3        Shooter up to speed
    	//   4        Disc fired
    	//   5        Against bar
    	//   6        Endgame (last 30 sec)
    	//   7        Autonomous mode
    	
    	//Set the number of discs
    	switch(numberOfDiscs) {
    		case 0:
    			lightsRelay1.set(Relay.Value.kOff);
    			lightsRelay2.setForward(false);
    			break;
    		case 1:
    			lightsRelay1.set(Relay.Value.kForward);
    			lightsRelay2.setForward(false);
    			break;
    		case 2:
    			lightsRelay1.set(Relay.Value.kReverse);
    			lightsRelay2.setForward(false);
    			break;
    		case 3:
    			lightsRelay1.set(Relay.Value.kOn);
    			lightsRelay2.setForward(false);
    			break;
    		default:
    			lightsRelay1.set(Relay.Value.kOff);
    			lightsRelay2.setForward(true);
    	}
    	
    	//Set remaining flag for lights
    	lightsRelay2.setReverse(shooterAtSpeed);
    	lightsRelay3.setForward(discFired);
    	lightsRelay3.setReverse(againstBar);
    	lightsRelay4.setForward(endGame);
    	lightsRelay4.setReverse(autoMode);
    	
    	System.out.println(numberOfDiscs);
    }

	/**
	 * @param shooterAtSpeed the shooterAtSpeed to set
	 */
	public static void setShooterAtSpeed(boolean value) {
		shooterAtSpeed = value;
	}

	/**
	 * @param discFired the discFired to set
	 */
	public static void setDiscFired(boolean value) {
		discFired = value;
		System.out.println(discFired);
	}

	/**
	 * @param againstBar the againstBar to set
	 */
	public static void setAgainstBar(boolean value) {
		againstBar = value;
	}

	/**
	 * @param endGame the endGame to set
	 */
	public static void setEndGame(boolean value) {
		endGame = value;
	}

	/**
	 * @param autoMode the autoMode to set
	 */
	public static void setAutoMode(boolean value) {
		autoMode = value;
	}

	/**
	 * @param numberOfDiscs the numberOfDiscs to set
	 */
	public static void setNumberOfDiscs(int discs) {
		numberOfDiscs = discs;
	}
}
