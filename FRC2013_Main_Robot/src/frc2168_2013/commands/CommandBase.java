package frc2168_2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc2168_2013.OI;
import frc2168_2013.subsystems.*;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    // Create a single static instance of all of your subsystems
    public static Drivetrain drivetrain = new Drivetrain();
    public static Intake         intake = new Intake();
    public static ShooterWheel       shooterWheel = new ShooterWheel();
    public static ShooterAngle       shooterAngle = new ShooterAngle();
    public static Hopper         hopper = new Hopper();
    public static Hanger         hanger = new Hanger();
    public static LightSaber     lightSaber = new LightSaber();

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();

        // Show what command your subsystem is running on the SmartDashboard
        SmartDashboard.putData(drivetrain);
        SmartDashboard.putData(intake);
        SmartDashboard.putData(shooterWheel);
        SmartDashboard.putData(hopper);
        SmartDashboard.putData(shooterAngle);
        SmartDashboard.putData(lightSaber);
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
    
    public static Drivetrain getDrivetrainInstance() {
    	return drivetrain;
    }
}
