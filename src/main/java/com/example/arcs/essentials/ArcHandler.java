package com.example.arcs.essentials;

import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;
import java.util.List;

// Handles the creation and manipulation of arcs
public class ArcHandler {
	/**
	 * The main object
	 */
	private OrthyArc orthyArc;
	/** The object used in dragging and rotating etc..*/
	private OrthyArc tempArc;
	/**
	 * I might need them later on :)
	 */
	private List<Point2D> tempArcPoints;
	/** records relative x and y co-ordinates.*/
	private class Delta { double x, y; }
	private boolean isSelected = false;
	/**
	 * Default constructor
	 * Basically you create an empty object, and you decorate it
	 * later on
	 */
	public ArcHandler(){
		//default OrthyArc created at a default constructor, can be modified and decorated
		this.orthyArc = new OrthyArc();
		this.tempArc = orthyArc;
		tempArcPoints = new ArrayList<>();
	}
	/**
	 * Arc creation
	 * Here we just create the arc, other method will draw it
	 * As a basic rule, Orthy objects should be created blank
	 * and decorated per need
	 */
	public void createArc(){
		/**
		 * After selectPoint filled the array with two points, im saving
		 * these points into two local variables, i use these points to
		 * initialize the arc in OrthyArch.
		 */
		Point2D p1 = orthyArc.getP1();
		Point2D p2 = orthyArc.getP2();
		Arc arcTemp = ArcFactory.createArc(p1, p2);
		/**
		 * Here i reset the point array, so when i press the drawArc button,
		 * the event handler that i have instantiated in my Controller class,
		 * will not prove true to the isInit()
		 */
		orthyArc.resetArcPoints();
		orthyArc.setArc(arcTemp);//this line overrides the old arc
		/**
		 * Here i copy the arc into the tempArc. The temp arc will be used
		 * for manupulations, and it wont be reset because there is no call
		 * to createArc() that contains the reset statements
		 */
		tempArc = orthyArc;
		//decorate the arc // TODO: 26/06/2023 Create a decorator class/methods
//		decorateArc(6, Color.BLACK,Color.BLACK);
		decorateArc(3, Color.BLACK,null);

	}
	public void drawArc(Pane drawingPane){
		drawingPane.getChildren().add(orthyArc.getArc());
	}
	/**
	 * Creates a point from the mouse event(mouse clicked)
	 * Adds the point to the OrthyArc point array
	 * Draws the point on the drawingPane
	 * @param event
	 * @param drawingPane
	 */
	public void selectPoints(MouseEvent event, Pane drawingPane){
		//create a tempPoint from the mouse click event
		Point2D tempPoint = new Point2D(event.getX(), event.getY());
		//add this point to orthyArc point list
		orthyArc.getArcPoints().add(tempPoint);
		//draw the the point on the drawingPane
		drawPoint(tempPoint, drawingPane);// TODO: 26/06/2023 Create a pen class
	}
	// Draws a point at a given position on a drawingPane
	public void drawPoint(Point2D p, Pane drawingPane){
		Circle point = new Circle(p.getX(), p.getY(), 2, Color.BLACK);
		drawingPane.getChildren().add(point);
	}
	//Arc dragging methods
	public void selectArc(Arc arc) {
		arc.setOnMouseClicked(e -> {
			if(isSelected) {
				arc.setStroke(Color.ALICEBLUE);
				isSelected = false;
			} else {
				arc.setStroke(Color.BLACK);
				isSelected = true;
			}
		});
	}
	public void makeArcDraggable(Arc arc) {
		final Delta dragDelta = new Delta();

		arc.setOnMousePressed(mouseEvent -> {
			// Record a delta distance for the drag operation
			dragDelta.x = arc.getCenterX() - mouseEvent.getX();
			dragDelta.y = arc.getCenterY() - mouseEvent.getY();
			arc.getScene().setCursor(Cursor.MOVE);
		});

		arc.setOnMouseReleased(mouseEvent -> {
			arc.getScene().setCursor(Cursor.HAND);
		});

		arc.setOnMouseDragged(mouseEvent -> {
			double newX = mouseEvent.getX() + dragDelta.x;
			if (newX > 0 && newX < arc.getScene().getWidth()) {
				arc.setCenterX(newX);
			}
			double newY = mouseEvent.getY() + dragDelta.y;
			if (newY > 0 && newY < arc.getScene().getHeight()) {
				arc.setCenterY(newY);
			}
		});

		arc.setOnMouseEntered(mouseEvent -> {
			if (!mouseEvent.isPrimaryButtonDown()) {
				arc.getScene().setCursor(Cursor.HAND);
			}
		});

		arc.setOnMouseExited(mouseEvent -> {
			if (!mouseEvent.isPrimaryButtonDown()) {
				arc.getScene().setCursor(Cursor.DEFAULT);
			}
		});
	}
	public void rotateArc(Arc arc){
		// These fields will hold data about the rotation
		final double[] startAngle = new double[1];
		final Point2D[] startDragPoint = new Point2D[1];
		arc.setOnMousePressed(event -> {
			if (event.isControlDown()) {
				// Calculate the initial angle when the drag starts
				startDragPoint[0] = new Point2D(event.getSceneX(), event.getSceneY());
				double dx = startDragPoint[0].getX() - arc.getCenterX();
				double dy = startDragPoint[0].getY() - arc.getCenterY();
				startAngle[0] = Math.toDegrees(Math.atan2(dy, dx));
			}
		});

		arc.setOnMouseDragged(event -> {
			if (event.isControlDown()) {
				// Calculate the current angle during the drag
				double dx = event.getSceneX() - arc.getCenterX();
				double dy = event.getSceneY() - arc.getCenterY();
				double currentAngle = Math.toDegrees(Math.atan2(dy, dx));

				// Rotate the arc by the difference between the start angle and current angle
				double rotation = currentAngle - startAngle[0];
				arc.getTransforms().add(new Rotate(rotation, arc.getCenterX(), arc.getCenterY()));

				// Update the startAngle for the next drag event
				startAngle[0] = currentAngle;
			}
		});
	}
	//Arc resizing methods
	//DECORATOR METHODS
	private void decorateArc(int strokeWidth, Color strokeColor, Color fill){
		orthyArc.getArc().setStrokeWidth(strokeWidth);
		orthyArc.getArc().setStroke(strokeColor);
		orthyArc.getArc().setFill(fill);
	}
	//HELPER METHODS
	private boolean isArcInitiated(){
		if(orthyArc.getArcPoints().size() == 0){
			return false;
		}
		if (orthyArc.getArcPoints().size() == 1){
			return false;
		}
		return true;
	}
	//GETTERS AND SETTERS
	public OrthyArc getOrthyArc() {
		return orthyArc;
	}
	public void setOrthyArc(OrthyArc orthyArc) {
		this.orthyArc = orthyArc;
	}
	public OrthyArc getTempArc() {
		return tempArc;
	}
	public void setTempArc(OrthyArc tempArc) {
		this.tempArc = tempArc;
	}
}
