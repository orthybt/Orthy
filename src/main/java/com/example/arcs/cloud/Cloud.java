package com.example.arcs.cloud;

import com.example.arcs.essentials.ArcHandler;
import com.example.arcs.essentials.CalibrationHandler;
import com.example.arcs.essentials.LineHandler;
import com.example.arcs.recycleBin.PointHandler;

/**
 * This class is the interface between the objects of the application
 */
public final class Cloud {
	private static Cloud instance;
	private Cloud() {}
	public static Cloud getInstance() {
		if (instance == null) {
			instance = new Cloud();
		}
		return instance;
	}




	/**
	 * The main objects
	 */
	private PointHandler pointHandler = new PointHandler();
	private CalibrationHandler calibrationHandler = new CalibrationHandler();
	private LineHandler lineHandler = new LineHandler();
	private ArcHandler arcHandler = new ArcHandler();




	/**
	 * Getters and setters
	 */
	public CalibrationHandler getCalibrationHandler() {
		return calibrationHandler;
	}
	public void setCalibrationHandler(CalibrationHandler calibrationHandler) {
		this.calibrationHandler = calibrationHandler;
	}
	public LineHandler getLineHandler() {
		return lineHandler;
	}
	public void setLineHandler(LineHandler lineHandler) {
		this.lineHandler = lineHandler;
	}
	public ArcHandler getArcHandler() {
		return arcHandler;
	}
	public void setArcHandler(ArcHandler arcHandler) {
		this.arcHandler = arcHandler;
	}
	public PointHandler getPointHandler() {
		return pointHandler;
	}
	public void setPointHandler(PointHandler pointHandler) {
		this.pointHandler = pointHandler;
	}
}
