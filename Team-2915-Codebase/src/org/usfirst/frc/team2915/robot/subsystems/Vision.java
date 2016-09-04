package org.usfirst.frc.team2915.robot.subsystems;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.vision.USBCamera;
import edu.wpi.first.wpilibj.vision.AxisCamera;


import com.ni.vision.NIVision;
import com.ni.vision.NIVision.*;


import org.usfirst.frc.team2915.robot.subsystems.CamSettings;

/**
 *
 */
public class Vision extends Thread implements Runnable{
    
	private final USBCamera shooterCam, loaderCam;
	private final AxisCamera visionCam;
	
	private final Image loadingImage = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
	
	public CameraView camToUse; //DO NOT CHANGE THIS FROM ANOTHER CLASS!!! Use setCam()!!!
	
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
			System.out.println("Succsesfully initalized USBCamera " + camName);
		}catch (Exception e){
			System.out.println("Failed to initalize USBCamera " + camName + ". Recived error: " + e);
		}
		
		return cam;
	}
	
	private AxisCamera getAxisCam(String camName){
		AxisCamera cam = null;
		try{
			cam = new AxisCamera(camName);
			System.out.println("Succsesfully initalized AxisCamera " + camName);
		}catch(Exception e){
			System.out.println("Failed to initalize AxisCamera " + camName + ". Recived error: " + e);
		}
		return cam;
	}
	
	public void run(){
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
			case VISION:
				visionCam.getImage(imageToSend);
				break;
			default:
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
			case VISION:
				loaderCam.stopCapture();
				shooterCam.stopCapture();
				break;
			default:
				break;
			}
		}catch(Exception e){
			System.out.println("setCam failed: " + e);
		}
		camToUse = camToShow;
	}
	
	public void getTargetData(){
		try{
			Image targetImage = null;
			
			visionCam.getImage(targetImage);
			
			Image bianaryImage = null;
			
			NIVision.imaqColorThreshold(bianaryImage, targetImage, 0x00FFFFFF, NIVision.ColorMode.HSV, CamSettings.hue, CamSettings.saturation, CamSettings.value);
			NIVision.imaqMorphology(bianaryImage, bianaryImage, NIVision.MorphologyMethod.PCLOSE, null);//we should expieriment with the morphiology methood
			
			
			
		}catch(Exception e){
			
		}
	}
}

