package org.usfirst.frc.team2915.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2915.robot.subsystems.TestCylinder;
import org.usfirst.frc.team2915.robot.*;

/**
 *
 */
public class testPistonWithButton extends Command {

    public testPistonWithButton() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.testCylinder);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try{
    		if (Robot.oi.getJoystick().getRawButton(6)){
    			Robot.testCylinder.set(DoubleSolenoid.Value.kReverse);
    		}else if (Robot.oi.getJoystick().getRawButton(4)){
    			Robot.testCylinder.set(DoubleSolenoid.Value.kForward);
    		}else{
    			Robot.testCylinder.set(DoubleSolenoid.Value.kOff);
    		}
    		
    	}catch(Exception e){
    		System.out.println(e);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
