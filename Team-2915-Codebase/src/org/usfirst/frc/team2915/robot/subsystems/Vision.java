package org.usfirst.frc.team2915.robot.subsystems;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.USBCamera;
import edu.wpi.first.wpilibj.vision.AxisCamera;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.*;

import org.usfirst.frc.team2915.robot.RobotMap;
import org.usfirst.frc.team2915.robot.subsystems.CamSettings;

/**
 *
 */
public class Vision extends Thread implements Runnable{
    
	private final USBCamera shooterCam, loaderCam;
	private final AxisCamera visionCam;
	
	private final Image loadingImage = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
	
	private CameraView camToUse;
	
	public Vision(){
		//initialize USB cams
		shooterCam = getCam(CamSettings.cameraNameShooter);
		loaderCam = getCam(CamSettings.cameraNameLoader);
		//initialize Axis Cam
		visionCam = getAxisCam(CamSettings.cameraNameVision);
		
		CameraServer.getInstance().setImage(loadingImage);
		setCam(CamSettings.defaultCam);
	}
	
	private USBCamera getCam(String camName){
		USBCamera cam = null;
		try{
			cam = new USBCamera(camName);
		}catch (Exception e){
			System.out.println("Failed to initalize USBCamera " + camName + ". Recived error: " + e);
		}
		
		return cam;
	}
	
	private AxisCamera getAxisCam(String camName){
		AxisCamera cam = null;
		try{
			cam = new AxisCamera(camName);
		}catch(Exception e){
			System.out.println("Failed to initalize AxisCamera " + camName + ". Recived error: " + e);
		}
		return cam;
	}
	
	public void Run(){
		//update cameras
		updateCams();
	}
	
	
	private void updateCams(){
		Image imageToSend = null;		
		
		try{
			switch(camToUse){
			case LOADER:
				loaderCam.getImage(imageToSend);
				break;
			case SHOOTER:
				shooterCam.getImage(imageToSend);
				break;
			}
		}catch(Exception e){
			System.out.println("Failed to get an image from cam " + camToUse + ".  Recived Error:" + e);
		}
		
		CameraServer.getInstance().setImage(imageToSend);
	}
	
	public void setCam(CameraView camToShow){
		try{
			switch(camToShow){
			case LOADER:
				shooterCam.stopCapture();
				loaderCam.startCapture();
				break;
			case SHOOTER:
				loaderCam.stopCapture();
				shooterCam.startCapture();
				break;
			}
		}catch(Exception e){
			System.out.println("setCam failed: " + e);
		}
	}
}

