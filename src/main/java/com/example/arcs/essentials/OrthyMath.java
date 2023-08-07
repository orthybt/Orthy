package com.example.arcs.essentials;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

import java.util.List;

public class OrthyMath{

	/**
	 * How many pixels is one millimeter
	 * @param p1 the first point
	 * @param p2 the second point
	 * @return the calibrated length in millimeters
	 */
	public static Double calibrate(Point2D p1, Point2D p2) {
		double valueInPixels = getLineLength(p1, p2);
		double oneMillimeter = valueInPixels / 10;
		/**
		 * Example, if i measure 100 pixels between two points, then one millimeter is 10 pixels
		 * If i have a length of 1000 pixels and the value for 1 mm is 10 px
		 * this means that 1000 px is 100 mm
		 * You divide the length in pixels by the value for 1 mm in pixels
		 */
		return oneMillimeter;
	}
	/**
	 * How many pixels is one millimeter
	 * @param calibrationPoints
	 * @return
	 */
	public static Double calibrate(List<Point2D> calibrationPoints) {
		Point2D p1 = calibrationPoints.get(0);
		Point2D p2 = calibrationPoints.get(1);
		double valueInPixels = p1.distance(p2);
		double oneMillimeter = valueInPixels / 10;
		/**
		 * Example, if i measure 100 pixels between two points, then one millimeter is 10 pixels
		 * If i have a length of 1000 pixels and the value for 1 mm is 10 px
		 * this means that 1000 px is 100 mm
		 * You divide the length in pixels by the value for 1 mm in pixels
		 */
		return oneMillimeter;
	}
	/**
	 * Calculates the length between two javafx.geometry.Point2D objects.
	 *
	 * @param p1 the first point
	 * @param p2 the second point
	 * @return the length between the two points
	 */
	public static Double getLineLength(Point2D p1, Point2D p2) {
		double dX = p1.getX() - p2.getX();
		double dY = p1.getY() - p2.getY();
		return Math.sqrt(dX * dX + dY * dY);
	}
	/**
	 * Calculates the length of a line in pixels using the Pythagorean theorem.
	 *
	 * @param line the line
	 * @return the length of the line
	 */
	public static Double getLineLength(Line line) {
		double dX = line.getStartX() - line.getEndX();
		double dY = line.getStartY() - line.getEndY();
		return Math.sqrt(dX * dX + dY * dY);
	}
	/**
	 * Calculates the length ratio between two JavaFX Line objects.
	 *
	 * @param lineOne the first line
	 * @param lineTwo the second line
	 * @return the length ratio between the two lines
	 */
	public static Double getLengthRatioBetweenTwoLines(Line lineOne, Line lineTwo) {
		Point2D p1 = new Point2D(lineOne.getStartX(), lineOne.getStartY());
		Point2D p2 = new Point2D(lineOne.getEndX(), lineOne.getEndY());
		Point2D p3 = new Point2D(lineTwo.getStartX(), lineTwo.getStartY());
		Point2D p4 = new Point2D(lineTwo.getEndX(), lineTwo.getEndY());
		double line1Length = getLineLength(p1, p2);
		double line2Length = getLineLength(p3, p4);
		double ratio = (line1Length * line2Length) / 100;
		return ratio;
	}
	/**
	 * Calculates the angle between three points: p1, center, and p3.
	 *
	 * @param p1     the first point
	 * @param center the center point
	 * @param p3     the third point
	 * @return the angle (in radians) between the lines formed by the three points
	 */
	public static Double getAngleBetweenThreePoints(Point2D p1, Point2D center, Point2D p3) {
		// Calculate the vectors representing the two lines
		Point2D line1 = p1.subtract(center);
		Point2D line2 = p3.subtract(center);

		// Calculate the lengths of the two lines
		double line1Length = getLineLength(center, p1);
		double line2Length = getLineLength(center, p3);

		// Calculate the product of the line lengths
		double lengthProduct = line1Length * line2Length;

		// Calculate the dot product of the two lines
		double dotProduct = line1.dotProduct(line2);

		// Calculate the angle between the two lines using the dot product and length product
		double theta = Math.acos(dotProduct / lengthProduct);

		return theta;
	}
	/**
	 * Finds the perpendicular intersection point of a given point and a line.
	 *
	 * @param point the point
	 * @param line  the line
	 * @return the perpendicular intersection point
	 */
	public static Point2D perpendicularIntersectionPoint(Point2D point, Line line) {
		// Translate the line to the given point
		Line translateLine = new Line(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());

		// Create a perpendicular line at point B of the translated line
		Line perLine = LineFactory.perpendicularLineAtB(translateLine);

		// Find the intersection point between the original line and the perpendicular line
		Point2D intersection = OrthyMath.findIntersection(line, perLine);

		return intersection;
	}
	/**
	 * Translates a line to a new starting point.
	 *
	 * @param destination the destination point
	 * @param startPoint  the current starting point of the line
	 * @param endPoint    the current ending point of the line
	 * @return a new Line with the destination as the start point and newEnd as the end point
	 */
	public static Line translateLine(Point2D destination, Point2D startPoint, Point2D endPoint) {
		// Calculate the differences in x and y coordinates
		double xDiff = endPoint.getX() - startPoint.getX();
		double yDiff = endPoint.getY() - startPoint.getY();

		// Calculate the new ending point coordinates based on the differences
		double newXEnd = destination.getX() + xDiff;
		double newYEnd = destination.getY() + yDiff;
		Point2D newEnd = new Point2D(newXEnd, newYEnd);

		// Create and return a new Line with the destination as the start point and newEnd as the end point
		return new Line(destination.getX(), destination.getY(), newEnd.getX(), newEnd.getY());
	}
	/**
	 * Translates a line to a new starting point.
	 *
	 * @param destination the destination point
	 * @param startPoint  the current starting point of the line
	 * @param endPoint    the current ending point of the line
	 * @return the new ending point of the translated line
	 */
	public static Point2D translateLineEndPoint(Point2D destination, Point2D startPoint, Point2D endPoint) {
		// Calculate the differences in x and y coordinates
		double xDiff = endPoint.getX() - startPoint.getX();
		double yDiff = endPoint.getY() - startPoint.getY();

		// Calculate the new ending point coordinates based on the differences
		double newXEnd = destination.getX() + xDiff;
		double newYEnd = destination.getY() + yDiff;
		Point2D newEnd = new Point2D(newXEnd, newYEnd);

		return newEnd;
	}
	/**
	 * Calculates the intersection point of two lines.
	 *
	 * @param l1 the first line
	 * @param l2 the second line
	 * @return the intersection point
	 */
	public static Point2D findIntersection(Line l1, Line l2) {
		// Calculate the coefficients of the line equations
		double a1 = l1.getEndY() - l1.getStartY();
		double b1 = l1.getStartX() - l1.getEndX();
		double c1 = a1 * l1.getStartX() + b1 * l1.getStartY();

		double a2 = l2.getEndY() - l2.getStartY();
		double b2 = l2.getStartX() - l2.getEndX();
		double c2 = a2 * l2.getStartX() + b2 * l2.getStartY();

		// Calculate the determinant
		double delta = a1 * b2 - a2 * b1;

		// Calculate the intersection point coordinates
		double x = (b2 * c1 - b1 * c2) / delta;
		double y = (a1 * c2 - a2 * c1) / delta;

		int xInt = (int) x;
		int yInt = (int) y;
		return new Point2D(xInt, yInt);
	}
	/**
	 * Finds the common center point of two circles defined by two points.
	 *
	 * @param p1 the first point on the circumference of the first circle
	 * @param p2 the second point on the circumference of the second circle
	 * @return the center point of the common circle
	 */
	public static Point2D findCommonCircle(Point2D p1, Point2D p2) {
		double centerX = (p1.getX() + p2.getX()) / 2;
		double centerY = (p1.getY() + p2.getY()) / 2;

		return new Point2D(centerX, centerY);
	}

	/**
	 * Finds the intersection point of the tangent lines drawn from two points to a common circle.
	 *
	 * @param p1 the first point
	 * @param p2 the second point
	 * @return the intersection point of the tangent lines
	 */
	public static Point2D findTangentIntersection(Point2D p1, Point2D p2) {
		// Create a line connecting the two points
		Line line = new Line(p1.getX(), p1.getY(), p2.getX(), p2.getY());

		// Create perpendicular lines at point p1 and point p2 of the line
		Line perP1 = LineFactory.perpendicularLineAtA(line);
		Line perP2 = LineFactory.perpendicularLineAtB(line);

		// Find the intersection point of the perpendicular lines
		Point2D intersection = findIntersection(perP1, perP2);

		return intersection;
	}

	/**
	 * Finds the intersection point between a line and a circle.
	 *
	 * @param intLine the line
	 * @param radius  the radius line of the circle
	 * @return the intersection point between the line and the circle
	 */
	public static Point2D findLineCircleIntersection(Line intLine, Line radius) {
		// Convert start and end points of the line to Point2D
		Point2D p1 = new Point2D(intLine.getStartX(), intLine.getStartY());
		Point2D p2 = new Point2D(intLine.getEndX(), intLine.getEndY());

		// Convert start point of the radius line to Point2D (which represents the center of the circle)
		Point2D center = new Point2D(radius.getStartX(), radius.getStartY());

		// Calculate the radius of the circle
		double r = getLineLength(radius);

		// Calculate the differences in x and y coordinates
		double off_X = p1.getX() - p2.getX();
		double off_Y = p1.getY() - p2.getY();

		// Calculate the length of the line
		double lineLength = Math.pow(off_X, 2) + Math.pow(off_Y, 2);

		// Check if the line intersects the circle
		if (lineLength < Math.pow(r, 2)) {
			System.out.println("There is no intersection between the line and the circle");
		}

		// Calculate the scaling factor
		double scale = r / Math.sqrt(lineLength);

		// Calculate the coordinates of the intersection point
		double x = off_X * scale + p2.getX();
		double y = off_Y * scale + p2.getY();

		return new Point2D(x, y);
	}
	/**
	 * Calculates the slope of a line defined by two points.
	 *
	 * @param p1 the first point
	 * @param p2 the second point
	 * @return the slope of the line
	 */
	public static Double calculateLineSlope(Point2D p1, Point2D p2) {
		return (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
	}
	/**
	 * Calculates the slope of a line perpendicular to the given slope.
	 *
	 * @param slope the slope of the original line
	 * @return the slope of the perpendicular line
	 */
	public static Double calculatePerpendicularLineSlope(Double slope) {
		return -1 / slope;
	}
	/**
	 * Calculates the midpoint of a line defined by two points.
	 *
	 * @param p1 the first point
	 * @param p2 the second point
	 * @return the midpoint of the line
	 */
	public static Point2D getMidpointOfLine(Point2D p1, Point2D p2) {
		double midX = (p1.getX() + p2.getX()) / 2;
		double midY = (p1.getY() + p2.getY()) / 2;
		return new Point2D(midX, midY);
	}
	/**
	 * Calculates the y-intercept of a line given its slope and an endpoint.
	 *
	 * @param slope     the slope of the line
	 * @param endPoint  the endpoint of the line
	 * @return the y-intercept of the line
	 */
	public static Double calculateY_Intercept(Double slope, Point2D endPoint) {
		return -slope * endPoint.getX() + endPoint.getY();
	}
	public static Point2D extrapolateRightTriangle(Point2D p1, Point2D p2, boolean useOption2) {
		double x1 = p1.getX();
		double y1 = p1.getY();
		double x2 = p2.getX();
		double y2 = p2.getY();

		// Option 1: p3 at x1, y2
		// Option 2: p3 at x2, y1

		if (useOption2) {
			// Use Option 2: p3 at x2, y1
			return new Point2D(x2, y1);
		} else {
			// Use Option 1: p3 at x1, y2 (default)
			return new Point2D(x1, y2);
		}
	}

}
