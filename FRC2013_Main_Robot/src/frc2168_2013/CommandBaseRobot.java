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
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc2168_2013.commands.CommandBase;
import frc2168_2013.commands.Auto.*;
import frc2168_2013.utils.SerialCommunicator;

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

    Compressor compressor;
    
    SendableChooser autoChooser;
    
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
        
        autoSelectInit();
        
    	System.out.println("ROBOT FINISHED LOADING!");
    }

    /**
     * This method is called once, when the robot first enters auto mode.
     */
    public void autonomousInit() {
    	Scheduler.getInstance().enable();
    	
    	// instantiate the command used for the autonomous period
        autonomousCommand = (Command) autoChooser.getSelected();
    	// schedule the autonomous command (example)
        autonomousCommand.start();
    }

    /**
     * This method is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This method is called once, when the robot first enters teleop mode.
     */
    public void teleopInit() {
    	Scheduler.getInstance().enable();
    	// This makes sure that the autonomous stops running when teleop starts
    	// running. If you want the autonomous to continue until interrupted by
    	// another command, remove this line or comment it out.
    	if(autonomousCommand != null)
    		autonomousCommand.cancel();
    	
    	armPositionInit.start();
        
        //Initialize the serial port for LEDs
        //SerialCommunicator.init(9600, 8, SerialPort.Parity.kNone, SerialPort.StopBits.kOne);
        //SerialCommunicator.putData("abcdefghijklmnopqrstuvwxyz");
    }

    /**
     * This method is called periodically during operator control
     */
    public void teleopPeriodic() {
    	Scheduler.getInstance().run();
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
    	
    	SerialCommunicator.free();
    }
    
    /**
     * This method gets called repeatedly while the robot is disabled.
     */
    public void disabledPeriodic() {
    	
    }
    
    private void autoSelectInit() {
        autoChooser = new SendableChooser();
        
        autoChooser.addDefault ("3 disc Auto", new FrontOfPyramid_3pt());
        
        SmartDashboard.putData("Autonomous mode", autoChooser);
    }
}
