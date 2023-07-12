package com.example.arcs.essentials;

/**
 * This class is the interface between the objects of the application
 */
public final class Cloud {
	/**
	 * This is a singleton class
	 */
	private static final Cloud INSTANCE = new Cloud();
	private Cloud() {
		this.calibrationHandler = new CalibrationHandler();
		this.lineHandler = new LineHandler();
		this.arcHandler = new ArcHandler();
	}
	public static Cloud getInstance() {
		return INSTANCE;
	}
	/**
	 * The main objects
	 */
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
}
