
package org.usfirst.frc.team2915.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team2915.robot.commands.SquareDance;
import org.usfirst.frc.team2915.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static final Chasis chasis = new Chasis();
	public static final Trigger trigger = new Trigger();
	public static final TestCylinder testCylinder = new TestCylinder();
	public static OI oi;
	
    //double gForceX = chasis.bAccelerometer.getX();
    //double gForceY = chasis.bAccelerometer.getY();
    //double gForceZ = chasis.bAccelerometer.getZ();
    
    public double gForceSustainedX = 0.0;
    public double gForceSustainedY = 0.0;
    public double gForceSustainedZPositive = 1.05;
    public double gForceSustainedZNegative = 0.95;
    
    public double xForceCap = 1.4;
    public double yForceCap = 1.4;

    Command autonomousCommand;
    SendableChooser camChooser;
    SendableChooser obstChooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();

		//configSmartDashboard();
		
     
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		oi.vision.run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
    	
    	autonomousCommand = new SquareDance();
    	if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	oi.vision.run();
        Scheduler.getInstance().run();
	    
	if (Math.abs(chasis.bAccelerometer.getX()) >= Math.abs(xForceCap)){
    	//Stop movement until driver input
    	autonomousCommand.cancel();
    		
    	}
    	if (Math.abs(chasis.bAccelerometer.getY()) >= Math.abs(yForceCap)){
    		//Stop movement until driver input
    		autonomousCommand.cancel();
    		
    	}
	    
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        //updateSmartDashboard();
        oi.vision.run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    private void configSmartDashboard(){
    	camChooser = new SendableChooser();
        
    	camChooser.addDefault("Loader", CameraView.LOADER);
        camChooser.addDefault("Shooter", CameraView.SHOOTER);
        camChooser.addDefault("Vision", CameraView.VISION);
        
        SmartDashboard.putData("Cam: ", camChooser);
    }
    
    private void updateSmartDashboard(){
//    	if (oi.vision.camToUse != (CameraView) camChooser.getSelected()){
//    		oi.vision.setCam((CameraView) camChooser.getSelected());
//    	}
	    
	testCollisionForce();
    	
    	SmartDashboard.putNumber("Accel X", chasis.bAccelerometer.getX());
    	SmartDashboard.putNumber("Accel Y", chasis.bAccelerometer.getY());
    	SmartDashboard.putNumber("Accel Z", chasis.bAccelerometer.getZ());
    	
    	SmartDashboard.putNumber("Sustained X", gForceSustainedX);
    	SmartDashboard.putNumber("Sustained Y", gForceSustainedY);
    	SmartDashboard.putNumber("Sustained Z (Down)", gForceSustainedZNegative);
    	SmartDashboard.putNumber("Sustained Z (Up)", gForceSustainedZPositive);
	    
    }
	
    public void testCollisionForce(){
    	
    	if (Math.abs(chasis.bAccelerometer.getX()) >= Math.abs(gForceSustainedY)){
    		gForceSustainedX = chasis.bAccelerometer.getX();
    	}
    	if (Math.abs(chasis.bAccelerometer.getY()) >= Math.abs(gForceSustainedY)){
    		gForceSustainedY = chasis.bAccelerometer.getY();
    	}
    	if (chasis.bAccelerometer.getZ() >= gForceSustainedZPositive){
    		gForceSustainedZPositive = chasis.bAccelerometer.getZ();
    	}
    	if (chasis.bAccelerometer.getZ() <= gForceSustainedZNegative){
    		gForceSustainedZNegative = chasis.bAccelerometer.getZ();
    	}
    	
    	
    }
    
    
}
