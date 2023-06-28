package com.example.arcs.essentials;

import com.example.arcs.essentials.OrthyMath;
import javafx.geometry.Point2D;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

/**
 * Arc Factory class
 * Creates different types of arcs, mainly used for orthodontic purposes, thus
 * the name of all the methods
 */
public class ArcFactory {
	public static Arc createArc(Point2D p1, Point2D p2){
		// The third point of a right triangle defined by p1 and p2
		Point2D c = (p1.getY() > p2.getY()) ?
				OrthyMath.extrapolateRightTriangle(p1, p2, true) :
				OrthyMath.extrapolateRightTriangle(p2, p1, false);

		// The center of the arc is at point c
		double centerX = c.getX();
		double centerY = c.getY();

		// The radii are the distances between c and p1, p2
		double radiusX = p1.distance(c);
		double radiusY = p2.distance(c);

		// Start angle depends on the vertical positions of the two points
		double startAngle = (p1.getY() > p2.getY()) ? 0 : 180;

		// Create the arc
		Arc arc = new Arc();
		arc.setCenterX(centerX);
		arc.setCenterY(centerY);
		arc.setRadiusX(radiusX);
		arc.setRadiusY(radiusY);
		arc.setStartAngle(startAngle);
		arc.setLength(180);
		arc.setType(ArcType.OPEN);

		// Return the arc
		return arc;
	}

}
