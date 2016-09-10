package org.usfirst.frc.team2915.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2915.robot.ControlMap;
import org.usfirst.frc.team2915.robot.Robot;

/**
 *
 */
public class DriveWithJoystick extends Command {

    public DriveWithJoystick() {
    	requires(Robot.chasis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try{
    		Robot.chasis.setMotors(Robot.oi.getJoystick().getRawAxis(ControlMap.axisSpeed), Robot.oi.getJoystick().getRawAxis(ControlMap.axisTurn));
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
    	Robot.chasis.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
