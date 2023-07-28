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
 * This is a persistent class that does not go out of scope
 */
public class LineHandler {
	/**
	 * The main object
	 */
	private OrthyLine line;
	/**
	 * Usefull objects
	 */
	private List<Point2D> linePoints;
	private boolean isLineInitialized;
	private double lineLengthTemp;
	/**
	 * Default constructor
	 * Basically you create an empty object, and you decorate it
	 * later on
	 */
	public LineHandler(){
		this.line = new OrthyLine();
		this.linePoints = new ArrayList<>();
		this.isLineInitialized = false;
	}
	public void drawLine(Pane drawingPane){
		decorateLine(1, Color.BLACK);
		drawingPane.getChildren().add(line.getLine());
		//chech if calibration is initialized and calculate line length, if
		// not, just draw the line
		if(Cloud.getInstance().getCalibrationHandler().isCalibrationInitialized()){
			lineLengthTemp = measureLine();
		}
		resetLine();
	}
	/**
	 * Line measurement
	 */
	public Double measureLine(){
		return line.getLineLength()/ Cloud.getInstance().getCalibrationHandler().getCalibrationFactor();
	}
	/**
	 * Reset line
	 */
	public void resetLine(){
		linePoints.clear();
		isLineInitialized = false;
		line = new OrthyLine();

	}
	/**
	 * Line decoration
	 */
	public void decorateLine(int width, Color color){
		line.getLine().setStrokeWidth(width);
		line.getLine().setStroke(color);
	}
	/**
	 * Returns the state of the line initialization
	 */
	public boolean isLineInitialized(){
		return line.isLineInitialized();
	}
	/**
	 * Getters and setters
	 */
	public OrthyLine getLine() {
		return line;
	}
	public void setOrthyLine(OrthyLine line) {
		this.line = line;
	}
	public List<Point2D> getLinePoints() {
		return linePoints;
	}
	public void setLinePoints(List<Point2D> linePoints) {
		this.linePoints = linePoints;
	}

	public double getLineLengthTemp() {
		return lineLengthTemp;
	}
}
