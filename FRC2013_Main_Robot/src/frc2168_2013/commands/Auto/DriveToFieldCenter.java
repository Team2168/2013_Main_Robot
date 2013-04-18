package frc2168_2013.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.CommandBaseRobot;
import frc2168_2013.commands.subSystems.DriveTrain.DriveDrivetrainStraight;
import frc2168_2013.commands.subSystems.DriveTrain.DriveDrivetrainTurn_Simple;

/**
 * Auto command. Sits still and shoots discs from close range at the three point goal.
 * 
 * @author James
 *
 */
public class DriveToFieldCenter extends CommandGroup {
	//These variables are listed in the order their associated commands
	//  will be executed. They are set below depending on initial position.
	private double driveDistance1, //in feet
                   rotateAngle1,   //in degrees
                   driveDistance2, //in feet
                   rotateAngle2;   //in degrees
	
	/**
	 * Drive to the center of the field.
	 */
	public DriveToFieldCenter() {
		int position = CommandBaseRobot.getInitialPosition();
		
		//Do different things depending on where we started from
		switch(position) {
			case CommandBaseRobot.RIGHT:
				driveDistance1 =  -1.0; //Drive backwards (ft)
				rotateAngle1   =  50.0; //Rotate clockwise (deg.)
				driveDistance2 =  -6.0; //Drive backwards (ft)
				rotateAngle2   =   0.0; //rotate clockwise (deg.)
				break;
			case CommandBaseRobot.CENTER:
				driveDistance1 =  -6.5; //Drive backwards (ft)
				rotateAngle1   =   0.0; //Rotate clockwise (deg.)
				driveDistance2 =   0.0; //Drive backwards (ft)
				rotateAngle2   =   0.0; //rotate clockwise (deg.)
				break;
			case CommandBaseRobot.LEFT:
				driveDistance1 =  -1.0; //Drive backwards (ft)
				rotateAngle1   = -45.0; //Rotate counter-clockwise (deg.)
				driveDistance2 =  -7.2; //Drive backwards (ft)
				rotateAngle2   =   0.0; //rotate counter-clockwise (deg.)
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
	 * This a value in feet to a number 
	 * This was derived empirically. 
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
