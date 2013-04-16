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
import frc2168_2013.utils.Enum;

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
    
    SendableChooser dashChooser;
	static SendableChooser initialPositionChooser;
	static SendableChooser afterShotChooser;
	
	//Delays (seconds) for shots in auto. These get set by the dashboard.
	private static double disc1Delay = 5.0,
                          disc2Delay = 0.5,
                          disc3Delay = 0.5;
	private static final String TIME_1_DELAY_KEY = "Delay before shot 1",
                                TIME_2_DELAY_KEY = "Delay before shot 2",
                                TIME_3_DELAY_KEY = "Delay before shot 3";
    
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
        compressor.start();
        
        //Initialize dashboard
        dashSelectInit();
        
        //Input where we start on the field - for use by auto modes
    	initialPositionChooser = new SendableChooser();
    	initialPositionChooser.addDefault("Center", new Integer(InitialPosition.CENTER));
    	initialPositionChooser.addObject("Left", new Integer(InitialPosition.LEFT));
    	initialPositionChooser.addObject("Right", new Integer(InitialPosition.RIGHT));
    	SmartDashboard.putData("Auto mode starting Position", initialPositionChooser);

    	//Add a radio button list to the dashboard to allow the operator to
    	//  choose what happens after our three discs are shot.
    	afterShotChooser = new SendableChooser();
    	afterShotChooser.addDefault("Sit Still", new Integer(AutoAfterShots.SIT_STILL));
    	afterShotChooser.addDefault("Defend center discs", new Integer(AutoAfterShots.DEFEND_CENTER));
    	afterShotChooser.addDefault("Move to protected loader", new Integer(AutoAfterShots.TO_PROTECTED_LOADER));
    	afterShotChooser.addDefault("Move to un-protected loader", new Integer(AutoAfterShots.TO_UNPROTECTED_LOADER));
    	SmartDashboard.putData("Auto mode destination position", afterShotChooser);
    	
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

    	//Get delays for disc shots
    	disc1Delay = SmartDashboard.getNumber(TIME_1_DELAY_KEY, 5.0);
    	disc2Delay = SmartDashboard.getNumber(TIME_2_DELAY_KEY, 0.5);
    	disc3Delay = SmartDashboard.getNumber(TIME_3_DELAY_KEY, 0.5);
    	
    	// instantiate and schedule the command used for the autonomous period
        autonomousCommand = new AutoSequencer();
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
    
    private void dashSelectInit() {
        dashChooser = new SendableChooser();
        dashChooser.addDefault("Competition Dash", new CompetitionDashboard());
        //dashChooser.addObject("PID Debug Dash", new ShooterWheelPIDDashboard());
        SmartDashboard.putData("Dashboard Chooser", dashChooser);
    }
    
    /**
     * Get the initial position of the robot as selected on the dashboard
     * @return the initial position
     */
    public static InitialPosition getInitialPosition() {
    	return (InitialPosition) initialPositionChooser.getSelected();
    }
    
    /**
     * The action the robot should take after it shoots its discs in auto mode
     * @return the action to perform
     */
    public static AutoAfterShots getAutoModeAfterShots() {
    	return (AutoAfterShots) afterShotChooser.getSelected();
    }
    
    /**
     * The time to delay before shooting the first disc in auto
     * @return time in seconds
     */
    public static double getDisc1Delay() {
    	return disc1Delay;
    }
    
    /**
     * The time to delay before shooting the second disc in auto
     * @return time in seconds
     */
    public static double getDisc2Delay() {
    	return disc2Delay;
    }
    
    /**
     * Time to delay before shooting the third disc in auto
     * @return time in seconds
     */
    public static double getDisc3Delay() {
    	return disc3Delay;
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
	
	public class InitialPosition extends Enum {
	    public static final int LEFT   = 1;
	    public static final int CENTER = 2;
	    public static final int RIGHT  = 3;

	    public InitialPosition(int enumValue) {
	        super(enumValue);
	    }
	}
	
	//What to do in auto after the discs are shot
	public class AutoAfterShots extends Enum {
		public static final int SIT_STILL             = 1; //Don't move after shooting
		public static final int DEFEND_CENTER         = 2; //Move to the center of the field and defend discs
		public static final int TO_PROTECTED_LOADER   = 3; //Move to the protected human load station
		public static final int TO_UNPROTECTED_LOADER = 4; //Move to the unprotected human load station
		
		public AutoAfterShots(int enumValue) {
			super(enumValue);
		}
	}
}
