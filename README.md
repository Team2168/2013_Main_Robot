FRC2013_Main_Robot
==================

This repository is for development of the 2013 Robot for the FIRST Robotics Competition.
It is written in Java and uses the Command Based Robot framework.

###Environment Setup
After cloning the repo and importing the project in eclipse, create an environment variable to fix your build path.  
1. Right click on the project folder, Build Path > Configure Build Path  
2. Click the Add Variable button  
3. Click the Configure Variables button  
4. Click the New button  
5. Type "USERPROFILE" in the name field (without quotes)  
6. Click the Folder button and browse to the directory which you extracted the sunspotfrcsdk folder to. e.g "C:\Users\Jim"


## Functionality
[robot name] has the following major subsystems. For more information on this robot, visit http://team2168.org/index.php/about-us/robots

#### Drivetrain
Single speed tank drive. Three motors per gear box (2x CIMs, 1x BAG?). Gear ratio?
X" diameter wheels, 4 per side.

#### Shooter
Single wheel linear shooter driven by a single CIM? motor.

#### Arm
2x BAG? motor driven linkage to raise and lower the arm.

#### Hopper
Single belt driven linear hopper. Front or rear loading. Can hold four discs.
Pneumatic actuators at muzzle act as mechanical stop for disks while filling from human player.
Digital sensors along hopper detect absence/presence of discs.

#### Intake
[TBD]

#### Hanger
Pnumatically operated two position linear lift for 10pt hang only.




