package org.usfirst.frc.team2915.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team2915.robot.ControlMap;
import org.usfirst.frc.team2915.robot.RobotMap;
import org.usfirst.frc.team2915.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 */
public class Chasis extends Subsystem {
    public Talon talonRightA = new Talon(RobotMap.motorPortRightA);
    public Talon talonRightB = new Talon(RobotMap.motorPortRightB);
    public Talon talonLeftA = new Talon(RobotMap.motorPortLeftA);
    public Talon talonLeftB = new Talon(RobotMap.motorPortLeftB);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	protected void initialize() {

		
		//correct direction of left talons to avoid confusing negatives
	
		
	}
	public Chasis(){
		talonLeftA.setInverted(true);
		talonLeftB.setInverted(true);
	}
	
    public void initDefaultCommand() {
    	//This makes DriveWithJoystick the default control for the chasis
    	//It is overriden by another command that requires the chasis
        setDefaultCommand(new DriveWithJoystick());
    }
    
    public void setMotors(double speed, double turn){
    	
    	talonRightA.set(speed + turn);
    	talonRightB.set(speed + turn);
    	talonLeftA.set(speed - turn);
    	talonLeftB.set(speed - turn);
    }
    
    public void stop(){
    	talonRightA.set(0);
    	talonRightB.set(0);
    	talonLeftA.set(0);
    	talonLeftB.set(0);
    }
    
    
}

