package com.example.arcs.essentials;

import com.example.arcs.cloud.Cloud;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

/**
 * You can access the calibration value from this class
 * It also handles the calibration process
 * Every object that needs the calibration value, meaning how many pixels
 * are in a 1mm distance, will have to ask this class for it.
 */
public class CalibrationHandler {
	/**
	 * The main object
	 */
	private List<Point2D> calibrationPoints;
	private Double calibrationFactor;
	private boolean isCalibrationInitialized;
	/**
	 * Default constructor
	 * Basically you create an empty object, and you decorate it
	 * later on
	 */
	public CalibrationHandler(){
		this.calibrationPoints = new ArrayList<>();
		this.calibrationFactor = null;
		this.isCalibrationInitialized = false;
	}
	/**
	 * The main method of this class
	 */
	public void calibrate(){
		Point2D p1 = calibrationPoints.get(0);
		Point2D p2 = calibrationPoints.get(1);
		this.calibrationFactor = OrthyMath.calibrate(p1, p2);
	}
	/**
	 * Resets the calibration
	 */
	public void resetCalibration() {
		calibrationPoints.clear();
		calibrationFactor = null;
		isCalibrationInitialized = false;
	}
	/**
	 * Returns the state of the calibration initialization
	 */
	public boolean isCalibrationInitialized(){
		return isCalibrationInitialized;
	}

	public void setCalibrationInitialized(boolean calibrationInitialized) {
		isCalibrationInitialized = calibrationInitialized;
	}

	/**
	 * Getters and setters
 	 */
	public List<Point2D> getCalibrationPoints() {
		return calibrationPoints;
	}
	public void setCalibrationPoints(List<Point2D> calibrationPoints) {
		this.calibrationPoints = calibrationPoints;
	}
	public Double getCalibrationFactor() {
		return calibrationFactor;
	}
	public void setCalibrationFactor(Double calibrationFactor) {
		this.calibrationFactor = calibrationFactor;
	}
}
