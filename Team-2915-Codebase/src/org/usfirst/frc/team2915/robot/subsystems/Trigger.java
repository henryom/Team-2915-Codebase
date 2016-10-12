package org.usfirst.frc.team2915.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Victor;
import org.usfirst.frc.team2915.robot.RobotMap;
import org.usfirst.frc.team2915.robot.commands.TriggerWithButtons;
import edu.wpi.first.wpilibj.CANTalon;

/**
 *
 */
public class Trigger extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	//public Jaguar jaguarTrigger = new Jaguar(RobotMap.triggerPort);
	
	public CANTalon talLeftTrig = new CANTalon(0);
	public CANTalon talRightTrig = new CANTalon(1);
	
	public Victor vexMotor = new Victor(8);
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TriggerWithButtons());
    }
    
    public void setTrigger(double speed){
//    	jaguarTrigger.set(speed);
    }
    
    public void setWheels(double speed){
    	talLeftTrig.set(-speed);
    	talRightTrig.set(speed);
    }
    
    public void stop(){
    	//jaguarTrigger.set(0);
    	talLeftTrig.set(0);
    	talRightTrig.set(0);
    }
    public void setVexMotor(double speed){
    	vexMotor.set(speed);
    }
}

