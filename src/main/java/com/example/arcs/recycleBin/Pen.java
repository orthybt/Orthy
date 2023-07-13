package com.example.arcs.recycleBin;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * A class that draws stuff on the drawingPane
 * it will have static methods that take points and panes to
 * draw stuff on
 */
public class Pen {
	public void drawPoint(Point2D point, Pane drawingPane){
		Circle drawPoint = new Circle(point.getX(), point.getY(), 2, Color.BLACK);
		drawingPane.getChildren().add(drawPoint);
	}
}
