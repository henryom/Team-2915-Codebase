package org.usfirst.frc.team2915.robot.commands;

import org.usfirst.frc.team2915.robot.ControlMap;
import org.usfirst.frc.team2915.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TriggerWithButtons extends Command {
	
	private boolean wheelsOn = false;

    public TriggerWithButtons() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.trigger);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try{
//    		if (Robot.oi.getJoystick().getRawButton(ControlMap.triggerUp)){
//    			Robot.trigger.setTrigger(0.1);	
//    		}else if (Robot.oi.getJoystick().getRawButton(ControlMap.triggerDown)){
//    			Robot.trigger.setTrigger(-0.1);
//    		}else{
//    			Robot.trigger.setTrigger(0);
//    		}
//    		if (Robot.oi.getJoystick().getRawButton(ControlMap.wheelToggle)){
//    			if (wheelsOn){
//    				Robot.trigger.setWheels(0.5);
//    			}else{
//    				Robot.trigger.setWheels(0.5);
//    			}
//    			wheelsOn = !wheelsOn;
//    		}
    		if (Robot.oi.getJoystick().getRawButton(7)){
    			Robot.trigger.setVexMotor(1);
    		}
    		if (Robot.oi.getJoystick().getRawButton(8)){
    			Robot.trigger.setVexMotor(0);
    		}
    		if (Robot.oi.getJoystick().getRawButton(11)){
    			Robot.trigger.setVexMotor(-1);
    		}
    		
    		if (Robot.oi.getJoystick().getRawButton(9)){
    			Robot.trigger.setWheels(1);
    		}
    		if (Robot.oi.getJoystick().getRawButton(10)){
    			Robot.trigger.stop();
    		}
    		
    	}catch (Exception e){
    		System.out.println(e);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.trigger.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
