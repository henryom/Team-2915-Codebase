package org.usfirst.frc.team2915.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team2915.robot.Robot;
/**
 *
 */
public class Drive extends Command {

	double leftSpeed;
	double rightSpeed;
	double timeToStopAt;
	
    public Drive(double time, double speedLeft, double speedRight) {
        requires(Robot.chasis);
        leftSpeed = speedLeft;
        rightSpeed = speedRight;
        timeToStopAt = Timer.getFPGATimestamp() + time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chasis.setMotorsIndv(leftSpeed, rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (timeToStopAt <= Timer.getFPGATimestamp()){
    		return true;
    	}else{
    		return false;
    	}
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
