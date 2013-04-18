package frc2168_2013.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.CommandBaseRobot;
import frc2168_2013.commands.subSystems.DriveTrain.DriveDrivetrainStraight;
import frc2168_2013.commands.subSystems.DriveTrain.DriveDrivetrainTurn_Simple;

/**
 * Drive to protected loading station.
 * Dependent on starting position.
 * 
 * @author James
 */
public class DriveToProtectedLoader extends CommandGroup {
	//These variables are listed in the order their associated commands
	//  will be executed. They are set below depending on initial position.
	private double driveDistance1, //in feet
                   rotateAngle1,   //in degrees
                   driveDistance2, //in feet
                   rotateAngle2;   //in degrees
	
	/**
	 * Drive to the position robot near the protected loading station.
	 */
	public DriveToProtectedLoader() {
		int position = CommandBaseRobot.getInitialPosition();
		
		//Do different things depending on where we started from
		switch(position) {
			case CommandBaseRobot.RIGHT:
				driveDistance1 =  -5.0; //Drive straight backwards to line (ft)
				rotateAngle1   =-145.0; //Rotate counter clockwise to face loading station(deg.)
				driveDistance2 =   0.0; //done
				rotateAngle2   =   0.0; //done
				break;
			case CommandBaseRobot.CENTER:
				driveDistance1 =  -4.0; //Drive backwards (ft)
				rotateAngle1   =  75.0; //Rotate clockwise to face loader wall
				driveDistance2 =   6.5; //Drive forwards across field (ft)
				rotateAngle2   =  75.0; //rotate to face human loader
				break;
			case CommandBaseRobot.LEFT:
				driveDistance1 =  -3.5; //Drive backwards (ft)
				rotateAngle1   =  46.0; //Rotate to face wall on loader side
				driveDistance2 =  12.0; //Drive forwards across field
				rotateAngle2   =  90.0; //rotate clockwise to face loader(deg.)
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
		if (in == 0.0) {
			return 0.0;
		} else {
			return ((in * 12) - 8);
		}
	}
}
