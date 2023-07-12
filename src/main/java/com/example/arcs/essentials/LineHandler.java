package com.example.arcs.essentials;

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
	 * Cloud access
	 */
	private Cloud c = Cloud.getInstance();
	/**
	 * The main object
	 */
	private OrthyLine line;
	/**
	 * Usefull objects
	 */
	private List<Point2D> linePoints;
	/**
	 * Default constructor
	 * Basically you create an empty object, and you decorate it
	 * later on
	 */
	public LineHandler(){
		this.line = new OrthyLine();
		this.linePoints = new ArrayList<>();
	}
	/**
	 * This is where the magic happens, initialization of the linePoints
	 * And thus the OrthyLine object
	 */
	public void selectPoint(MouseEvent event, Pane drawingPane){
		//create a tempPoint from the mouse click event
		Point2D tempPoint = new Point2D(event.getX(), event.getY());
		//add this point to linePoints list
		linePoints.add(tempPoint);
		//draw the the point on the drawingPane
		drawPoint(tempPoint, drawingPane);// TODO: 26/06/2023 Create a pen class
	}
	public void drawPoint(Point2D p, Pane drawingPane){
		Circle point = new Circle(p.getX(), p.getY(), 2, Color.BLACK);
		drawingPane.getChildren().add(point);
	}
	/**
	 * Line Creation
	 */
	public void createLine(){
		Point2D p1 = linePoints.get(0);
		Point2D p2 = linePoints.get(1);
		line = new OrthyLine(p1, p2);
	}
	public void drawLine(Pane drawingPane){
		decorateLine(1, Color.BLACK);
		drawingPane.getChildren().add(line.getLine());
	}
	/**
	 * Line measurement
	 */
	public Double measureLine(){
		return line.getLineLengthMM();
	}
	/**
	 * Reset line
	 */
	public void resetLine(){
		linePoints.clear();
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
		return linePoints.size() == 2;
	}
	/**
	 * Getters and setters
	 */
	public OrthyLine getLine() {
		return line;
	}
	public void setLine(OrthyLine line) {
		this.line = line;
	}
	public List<Point2D> getLinePoints() {
		return linePoints;
	}
	public void setLinePoints(List<Point2D> linePoints) {
		this.linePoints = linePoints;
	}
}
