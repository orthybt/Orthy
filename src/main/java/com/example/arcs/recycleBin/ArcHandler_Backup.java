package com.example.arcs.recycleBin;

import com.example.arcs.essentials.ArcFactory;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

// Handles the creation and manipulation of arcs
public class ArcHandler_Backup {

	private Point2D point1;
	private Point2D point2;
	private Point2D mousePressPoint;

	private List<Point2D> arcPoints = new ArrayList<>();
	private Arc arc;
	public List<Point2D> getArcPoints() {
		return arcPoints;
	}
	public void setArcPoints(List<Point2D> arcPoints) {
		this.arcPoints = arcPoints;
	}

	// Resets the points of the arc
	public void reset(){
		this.point1 = null;
		this.point2 = null;
	}
	// Checks if the arc needs resetting
	public boolean isReset(){
		return arcPoints.size() < 3;
	}
	// Draws a point at a given position on a pane
	public void drawPoint(Point2D p, Pane pane){
		Circle point = new Circle(p.getX(), p.getY(), 2, Color.BLACK);
		pane.getChildren().add(point);
	}
	// Initializes the points of the arc
	public void initPoints(){
		this.point1 = arcPoints.get(0);
		this.point2 = arcPoints.get(1);
	}
	// Resets the arc
	public void resetArc(){
		this.arcPoints.clear();
		this.point1 = null;
		this.point2 = null;
	}
	// Initializes the arc and adds it to the drawing pane
	public void initArc(Pane drawingPane) {
		initPoints();
		this.arc = ArcFactory.createArc(point1, point2);
		this.arc.setFill(null);
		this.arc.setStrokeWidth(1);
		this.arc.setStroke(Color.BLACK);
		drawingPane.getChildren().add(this.arc);
		// Change cursor when mouse enters or exits the arc
		this.arc.setOnMouseEntered(event -> drawingPane.setCursor(Cursor.HAND));
		this.arc.setOnMouseExited(event -> drawingPane.setCursor(Cursor.DEFAULT));
		setupMouseEvents(drawingPane);
	}
	public void setupMouseEvents(Pane drawingPane){
		setupMousePress();
		setupMouseDrag(drawingPane);
	}
	// Sets up the mouse press event
	public void setupMousePress() {
		arc.setOnMousePressed(event -> {
			mousePressPoint = new Point2D(event.getX(), event.getY());
		});
	}
	// Sets up the mouse drag event
	public void setupMouseDrag(Pane drawingPane) {
		arc.setOnMouseDragged(event -> {
			// Calculate the displacement of the drag
			Point2D dragPoint = new Point2D(event.getX(), event.getY());
			Point2D displacement = dragPoint.subtract(mousePressPoint);
			// Move the arc
			moveArc(displacement, drawingPane);
			// Update the mouse press point to the current point
			mousePressPoint = dragPoint;
		});
	}
	// Moves the arc based on a displacement
	public void moveArc(Point2D displacement, Pane drawingPane) {
		// If points are not initialized, simply return
		if(point1 == null || point2 == null) {
			return;
		}

		// Update the arc's points
		point1 = point1.add(displacement);
		point2 = point2.add(displacement);
		for(int i=0; i<arcPoints.size(); i++) {
			arcPoints.set(i, arcPoints.get(i).add(displacement));
		}

		// Update the arc itself
		arc.setCenterX(arc.getCenterX() + displacement.getX());
		arc.setCenterY(arc.getCenterY() + displacement.getY());

		// Redraw the arc
		drawingPane.getChildren().remove(arc);
		initArc(drawingPane);
	}
	// Handles mouse clicks and adds a point to the arc
	public void handleMouseClick(MouseEvent event, Pane drawingPane){
		Point2D tempPoint = new Point2D(event.getX(), event.getY());
		drawPoint(tempPoint, drawingPane);
		arcPoints.add(tempPoint);
	}
	public void handleMouseDrag(MouseEvent event, Pane drawingPane){

	}

}
