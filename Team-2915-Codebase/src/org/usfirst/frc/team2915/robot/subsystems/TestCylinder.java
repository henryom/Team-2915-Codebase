package org.usfirst.frc.team2915.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2915.robot.commands.testPistonWithButton;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 */
public class TestCylinder extends Subsystem {
	public DoubleSolenoid solTest = new DoubleSolenoid(0,1);
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new testPistonWithButton());
    }
    
    public void set(DoubleSolenoid.Value dir){
    	solTest.set(dir);
    }
}

