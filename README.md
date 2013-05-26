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
Phoenix has the following major subsystems. For more information on this robot, visit http://team2168.org/index.php/about-us/robots

#### Drivetrain
Single speed tank drive. Three motors per gear box (3x CIMs). Gear ratio?
4" diameter wheels, 4 per side, drop center.

#### Shooter
Two wheel linear shooter.
Two pneumaticly controlled positions for shooting from front and back of pyramid.

#### Hopper
Bucket hopper. Pnumatic actuator to pull disc into shooter wheels.
No sensors for number of discs.

#### Intake
3 position, pneumatically raised/lowered.
1. Stow - raised to its highest position, out of the way forhuman load, this is where it starts at the beginning of a match.
2. Hopper - this is the position where discs can be dumped into the hopper
3. Load - The lowered position where discs can be collected from the floor.

Can hold two discs side by side. AM entraption stars on two independent rollers driven by motors. Once stopped, the compression of the stars on the disc keep them captive until driven again.
Limit switches on each side can detect the presence of a disc.

#### Hanger
Pnumatically operated two position linear lift for 10pt hang only.

#### Light sabers / Blocker
Pnumaticaally operated fiberglass rods to raise and lower to aid in lineup to the pyramid. When raised they engage the 30" bar of the pyramid slightly. When lowered they retract well below the 30" bar.
An optional blocker mechanism can be added and plumbed to the solenoid valves for the light sabers. No code changes necessary.

