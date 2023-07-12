package com.example.arcs.essentials;

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
	 * Cloud access
	 */
	private Cloud c = Cloud.getInstance();
	/**
	 * The main object
	 */
	private List<Point2D> calibrationPoints;
	private Double calibrationFactor;
	/**
	 * Default constructor
	 * Basically you create an empty object, and you decorate it
	 * later on
	 */
	public CalibrationHandler(){
		this.calibrationPoints = new ArrayList<>();
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
	 * Creates a point from the mouse event and draws it on the drawingPane
	 * Adds the point to the point list
	 * Draws the point on the drawingPane
	 * @param event
	 * @param drawingPane
	 */
	public void selectPoint(MouseEvent event, Pane drawingPane){
		//create a tempPoint from the mouse click event
		Point2D tempPoint = new Point2D(event.getX(), event.getY());
		//add this point to calibration point list
		calibrationPoints.add(tempPoint);
		//draw the the point on the drawingPane
		drawPoint(tempPoint, drawingPane);// TODO: 26/06/2023 Create a pen class
	}
	public void drawPoint(Point2D p, Pane drawingPane){
		Circle point = new Circle(p.getX(), p.getY(), 2, Color.BLACK);
		drawingPane.getChildren().add(point);
	}
	/**
	 * Resets the calibration
	 */
	public void resetCalibration() {
		calibrationPoints.clear();
		calibrationFactor = null;
		Printer.print("Calibration reset is successful");
	}
	/**
	 * Returns the state of the calibration initialization
	 */
	public boolean isCalibrationInitialized(){
		return calibrationPoints.size() == 2;
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
