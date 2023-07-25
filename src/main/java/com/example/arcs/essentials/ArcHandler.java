package com.example.arcs.essentials;

import com.example.arcs.cloud.Cloud;
import com.example.arcs.recycleBin.Pen;
import com.example.arcs.recycleBin.PointSelector;
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
	private OrthyArc orthyArc;
	private List<Point2D> arcPoints;
	private class Delta { double x, y; }
	private boolean isSelected = false;
	public ArcHandler(){
		//default OrthyArc created at a default constructor, can be modified and decorated
		this.orthyArc = new OrthyArc();
		this.arcPoints = new ArrayList<>();
	}
	public void drawArc(Pane drawingPane){
		decorateArc(2, Color.BLACK, Color.TRANSPARENT);
		drawingPane.getChildren().add(orthyArc.getArc());
		resetArc();

	}
	public boolean isArcInitialized(){
		return orthyArc.isArcInitialized();
	}
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
	private void decorateArc(int strokeWidth, Color strokeColor, Color fill){
		orthyArc.getArc().setStrokeWidth(strokeWidth);
		orthyArc.getArc().setStroke(strokeColor);
		orthyArc.getArc().setFill(fill);
	}
	public void resetArc(){
		this.orthyArc.resetArcPoints();
	}

	//GETTERS AND SETTERS
	public void setOrthyArc(OrthyArc orthyArc) {
		this.orthyArc = orthyArc;
	}
	public OrthyArc getOrthyArc() {
		return orthyArc;
	}
	public List<Point2D> getArcPoints() {
		return arcPoints;
	}
	public void setArcPoints(List<Point2D> arcPoints) {
		this.arcPoints = arcPoints;
	}
//	public void setOrthyArc(OrthyArc orthyArc) {
//		this.orthyArc = orthyArc;
//	}
//	public OrthyArc getTempArc() {
//		return tempArc;
//	}
//	public void setTempArc(OrthyArc tempArc) {
//		this.tempArc = tempArc;
//	}
}
