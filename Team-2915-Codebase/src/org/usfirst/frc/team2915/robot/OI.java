package org.usfirst.frc.team2915.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team2915.robot.ControlMap;
import org.usfirst.frc.team2915.robot.subsystems.Vision;;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    Joystick joy;
    //public final Vision vision = new Vision();
    
	public OI(){
		joy = new Joystick(ControlMap.joystickPortA);
	}
	
	public Joystick getJoystick(){
		return joy;
	}
	
}

