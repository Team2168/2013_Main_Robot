package frc2168_2013.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.RobotMap;
import frc2168_2013.commands.Sleep;
import frc2168_2013.commands.subSystems.DriveTrain.DriveDrivetrainStraight;
import frc2168_2013.commands.subSystems.Hopper.ShootSingleDisc;
import frc2168_2013.commands.subSystems.Intake.DriveIntakeMotors;
import frc2168_2013.commands.subSystems.Intake.IntakeHopperPosition;
import frc2168_2013.commands.subSystems.Intake.IntakeLoadPosition;
import frc2168_2013.commands.subSystems.ShooterAngle.ShooterAngleExtend;
import frc2168_2013.commands.subSystems.ShooterAngle.ShooterAngleStow;
import frc2168_2013.commands.subSystems.ShooterWheel.DriveShooterWithConstant;
import frc2168_2013.commands.subSystems.ShooterWheel.DriveShooterWithJoystick;
import frc2168_2013.commands.subSystems.ShooterWheel.PID_SetFwdWheelSpeed;
import frc2168_2013.commands.subSystems.ShooterWheel.PID_ShooterPause;
import frc2168_2013.commands.subSystems.ShooterWheel.PID_SetAftWheelSpeed;

/**
 * Auto command. Sits still and shoots discs from close range at the three point goal.
 * 
 * @author Shriji
 *
 */
public class RearOfPyramid_3pt_Center_FiveDisc extends CommandGroup {
	
	private double firstDiscTime;
	private double secondDiscTime;
	private double thirdDiscTime;
	
	public RearOfPyramid_3pt_Center_FiveDisc(double firstDiscTime, double secondDiscTime, double thirdDiscTime) {
		
		this.firstDiscTime = firstDiscTime;
		this.secondDiscTime = secondDiscTime;
		this.thirdDiscTime = thirdDiscTime;
		
		//set intake to hopper position
		addSequential(new IntakeHopperPosition());
		
		//set shooter angle to stow position
		addParallel(new ShooterAngleStow());
		
		//driver both shooterwheels at full speed
		addParallel(new DriveShooterWithConstant(1, 1));
		
		//Shoot three discs
		addSequential(new Sleep(), firstDiscTime);
		addSequential(new ShootSingleDisc());
		addSequential(new Sleep(), secondDiscTime);
		addSequential(new ShootSingleDisc());
		addSequential(new Sleep(), thirdDiscTime);
		
		//set intake to load position
		addSequential(new IntakeLoadPosition());
		
		//drive intake motors for 3 seconds
		addParallel(new DriveIntakeMotors( 1.0, 1.0 ), 3);
		
		//drive forward 4ft 
		addSequential(new DriveDrivetrainStraight(((4*12)-8-7.5)));
		
		//set intake to hopper position
		addSequential(new IntakeHopperPosition());
		
		//drive intake motors for 3 seconds
		addParallel(new DriveIntakeMotors( 1.0, 1.0 ), 3);
		
		//drive Back 4ft
		addSequential(new DriveDrivetrainStraight(-((4*12)-8-7.5)));
				
		//shoot two disks
		addSequential(new Sleep(), firstDiscTime);
		addSequential(new ShootSingleDisc());
		addSequential(new Sleep(), secondDiscTime);
		addSequential(new ShootSingleDisc());
		
		//drive back 3 feet
		addSequential(new DriveDrivetrainStraight(-((3*12)-8-7.5)));

	}
}
