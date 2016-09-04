package org.usfirst.frc.team2915.robot.subsystems;

import com.ni.vision.NIVision.Range;

public class CamSettings {
	
	//defaults
	public final static CameraView defaultCam = CameraView.SHOOTER;
	
	//camera names
	public static String cameraNameShooter = "shooter";
	public static String cameraNameLoader = "loader";
	public static String cameraNameVision = "visionCam.local";
	
	//image processing values
	public static Range hue = new Range(200,250);
	public static Range saturation = new Range(200,250);
	public static Range value = new Range(200,250);
	
}
