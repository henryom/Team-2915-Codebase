package org.usfirst.frc.team2915.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team2915.robot.commands.ExampleCommand;
import org.usfirst.frc.team2915.robot.ControlMap;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    Joystick joy;
    
	public OI(){
		joy = new Joystick(ControlMap.joystickPortA);
	}
	
	public Joystick getJoystick(){
		return joy;
	}
	
}

