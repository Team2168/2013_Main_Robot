package frc2168_2013.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.CommandBaseRobot;
import frc2168_2013.CommandBaseRobot.InitialPosition;
import frc2168_2013.commands.subSystems.DriveTrain.DriveDrivetrainStraight;
import frc2168_2013.commands.subSystems.DriveTrain.DriveDrivetrainTurn_Simple;

/**
 * Auto command to drive to the unprotected loading station.
 * Dependent on starting position.
 * 
 * @author James
 */
public class DriveToUnprotectedLoader extends CommandGroup {
	//These variables are listed in the order their associated commands
	//  will be executed. They are set below depending on initial position.
	private double driveDistance1, //in feet
                   rotateAngle1,   //in degrees
                   driveDistance2, //in feet
                   rotateAngle2;   //in degrees
	
	/**
	 * Drive to the position robot near the unprotected loading station.
	 */
	public DriveToUnprotectedLoader() {
		InitialPosition position = CommandBaseRobot.getInitialPosition();
		
		//Do different things depending on where we started from
		switch(position.Value()) {
			case InitialPosition.RIGHT:
				driveDistance1 =  -2.0; //Drive backwards (ft)
				rotateAngle1   = -40.0; //Rotate to face wall on loader side
				driveDistance2 =  17.0; //Drive forwards across field
				rotateAngle2   = -90.0; //rotate to face loader(deg.)
				break;
			case InitialPosition.CENTER:
				driveDistance1 =  -5.0; //Drive backwards (ft)
				rotateAngle1   = -90.0; //Rotate to face loader wall 
				driveDistance2 =  10.0; //Drive forwards across field (ft)
				rotateAngle2   = -90.0; //rotate to face human loader station
				break;
			case InitialPosition.LEFT:
				driveDistance1 =  -7.0; //Drive straight backwards to line (ft)
				rotateAngle1   = 140.0; //Rotate clockwise to face loading station(deg.)
				driveDistance2 =   0.0; //done
				rotateAngle2   =   0.0; //done
				break;
			default: //just in case
				break;
		}
		//drive backwards
		addSequential(new DriveDrivetrainStraight(convertDistance(driveDistance1)));
		//rotate the chassis
		addSequential(new DriveDrivetrainTurn_Simple(rotateAngle1));
		//drive backwards
		addSequential(new DriveDrivetrainStraight(convertDistance(driveDistance2)));
		//rotate the chassis
		addSequential(new DriveDrivetrainTurn_Simple(rotateAngle2));
	}
	
	/**
	 * Adjust value in feet to a number that gets us closer to actual measured
	 * distance. This was derived empirically. 
	 * 
	 * @param in the distance in feet to be converted
	 * @return the converted value in inches
	 */
	private double convertDistance (double in) {
		return ((in * 12) - 8);
	}
}
