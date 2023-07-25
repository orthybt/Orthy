package com.example.arcs.recycleBin;


import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * PointSelector creates points and adds them to a list
 * That is all it does
 * You can access the list of points by calling getPoints()
 */
public class PointSelector implements EventHandler<MouseEvent>{
	private List<Point2D> points;
	//Default constructor that initializes an empty list of points
	public PointSelector(){
		this.points = new ArrayList<>();
	}
	@Override
	public void handle(MouseEvent event) {
		Point2D tempPoint = new Point2D(event.getX(), event.getY());
		points.add(tempPoint);
	}
	public List<Point2D> getPoints(){
		return points;
	}
	public void resetPointSelector(){
		points.clear();
	}
}
