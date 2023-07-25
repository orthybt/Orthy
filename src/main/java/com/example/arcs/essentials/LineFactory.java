package com.example.arcs.essentials;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class LineFactory {
	/**
	 * Basic line creation
	 */
	public static Line createLine(Point2D p1, Point2D p2) {
		return new Line(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}
	public static OrthyLine createOrthyLine(Point2D p1, Point2D p2) {
		OrthyLine line = new OrthyLine(p1, p2);
		line.setA(p1);
		line.setB(p2);
		line.setLine(createLine(p1, p2));
		line.setLineInitialized(true);
		return line;
	}
	public static Line horizontalLine(Point2D p) {
		double x = p.getX();
		double y = p.getY();
		Point2D start = new Point2D(x - 5000, y);
		Point2D end = new Point2D(x + 5000, y);
		return new Line(start.getX(), start.getY(), end.getX(), end.getY());
	}
	public static Line verticalLine(Point2D p) {
		double x = p.getX();
		double y = p.getY();
		Point2D start = new Point2D(x, y - 5000);
		Point2D end = new Point2D(x, y + 5000);
		return new Line(start.getX(), start.getY(), end.getX(), end.getY());
	}
	public static Line horizontalIntersectionLine(Point2D start, Line line) {
		Line horizontalLine = horizontalLine(start);
		Point2D intersection = OrthyMath.findIntersection(horizontalLine, line);
		return new Line(start.getX(), start.getY(), intersection.getX(), intersection.getY());
	}
	public static Line verticalIntersectionLine(Point2D start, Line line) {
		Line verticalLine = verticalLine(start);
		Point2D intersection = OrthyMath.findIntersection(verticalLine, line);
		return new Line(start.getX(), start.getY(), intersection.getX(), intersection.getY());
	}
	public static Line perpendicularIntersectionLine(Line line, Point2D destination) {
		Line extLine = extendLine(line);
		Line translateLine = translateLine(destination, extLine);
		Line perLineDestination = perpendicularLineAtA(translateLine);
		Point2D intersection = OrthyMath.findIntersection(extLine, perLineDestination);
		return new Line(intersection.getX(), intersection.getY(), destination.getX(), destination.getY());
	}
	public static Line extendLine(Line line) {
		Point2D a = getPoint(line, 1);
		Point2D b = getPoint(line, 2);
		Line AB = line;
		Line BA = new Line(b.getX(), b.getY(), a.getX(), a.getY());
		Line lineOne = translateLine(b, AB);
		Line lineTwo = new Line(a.getX(), a.getY(), getPoint(lineOne, 2).getX(), getPoint(lineOne, 2).getY());
		Line lineThree = translateLine(getPoint(lineTwo, 2), lineTwo);
		Line lineFour = createLine(a, getPoint(lineThree, 2));
		Line lineFive = translateLine(getPoint(lineFour, 2), lineFour);
		Line lineSix = createLine(a, getPoint(lineFive, 2));
		Line lineSeven = translateLine(getPoint(lineSix, 2), lineSix);
		Line lineEight = createLine(a, getPoint(lineSeven, 2));
		Line bOne = translateLine(b, BA);
		Line bTwo = new Line(b.getX(), b.getY(), getPoint(bOne, 2).getX(), getPoint(bOne, 2).getY());
		Line bThree = translateLine(getPoint(bTwo, 2), bTwo);
		Line bFour = createLine(b, getPoint(bThree, 2));
		Line bFive = translateLine(getPoint(bFour, 2), bFour);
		Line bSix = createLine(b, getPoint(bFive, 2));
		Line bSeven = translateLine(getPoint(bSix, 2), bSix);
		Line bNine = createLine(b, getPoint(bSeven, 2));

		// Create the extended line
		Line extendedLine = createLine(getPoint(bNine, 2), getPoint(lineEight, 2));

		return extendedLine;
	}
	public static Line translateLine(Point2D destination, Line line) {
		double xDiff = destination.getX() - line.getStartX();
		double yDiff = destination.getY() - line.getStartY();
		double newXStart = line.getStartX() + xDiff;
		double newYStart = line.getStartY() + yDiff;
		double newXEnd = line.getEndX() + xDiff;
		double newYEnd = line.getEndY() + yDiff;
		return new Line(newXStart, newYStart, newXEnd, newYEnd);
	}
	public static Line perpendicularLineAtMidPoint(Line line) {
		Point2D a = getPoint(line, 1);
		Point2D b = getPoint(line, 2);
		Point2D midPoint = getMidpointOfLine(a, b);
		double slope = calculateLineSlope(a, b);
		double negativeSlope = calculatePerpendicularLineSlope(slope);
		double yIntercept = calculateYIntercept(slope, b);
		double yInterceptPerpendicular = calculateYIntercept(negativeSlope, midPoint);
		Point2D perpendicularStart = new Point2D(0, yInterceptPerpendicular);
		Line perpendicularStartMidpoint = createLine(perpendicularStart, midPoint);
		Line perLine = extendLine(perpendicularStartMidpoint);
		return perLine;
	}
	public static Line perpendicularLineAtA(Line line) {
		Point2D a = getPoint(line, 1);
		Point2D b = getPoint(line, 2);
		Point2D midPoint = getMidpointOfLine(a, b);
		double slope = calculateLineSlope(a, b);
		double negativeSlope = calculatePerpendicularLineSlope(slope);
		double yIntercept = calculateYIntercept(slope, b);
		double yInterceptPerpendicular = calculateYIntercept(negativeSlope, midPoint);
		Point2D perpendicularStart = new Point2D(0, yInterceptPerpendicular);
		Line perpendicularStartMidpoint = createLine(perpendicularStart, midPoint);
		Line translateLine = translateLine(getPoint(line, 1), perpendicularStartMidpoint);
		Line perLine = extendLine(translateLine);
		return perLine;
	}
	public static Line perpendicularLineAtB(Line line) {
		Point2D a = getPoint(line, 1);
		Point2D b = getPoint(line, 2);
		Point2D midPoint = getMidpointOfLine(a, b);
		double slope = calculateLineSlope(a, b);
		double negativeSlope = calculatePerpendicularLineSlope(slope);
		double yIntercept = calculateYIntercept(slope, b);
		double yInterceptPerpendicular = calculateYIntercept(negativeSlope, midPoint);
		Point2D perpendicularStart = new Point2D(0, yInterceptPerpendicular);
		Line perpendicularStartMidpoint = createLine(perpendicularStart, midPoint);
		Line translateLine = translateLine(getPoint(line, 2), perpendicularStartMidpoint);
		Line perLine = extendLine(translateLine);
		return perLine;
	}
	private static Point2D getMidpointOfLine(Point2D a, Point2D b) {
		double midX = (a.getX() + b.getX()) / 2.0;
		double midY = (a.getY() + b.getY()) / 2.0;
		return new Point2D(midX, midY);
	}
	private static double calculateLineSlope(Point2D a, Point2D b) {
		return (b.getY() - a.getY()) / (b.getX() - a.getX());
	}
	private static double calculatePerpendicularLineSlope(double slope) {
		return -1 / slope;
	}
	private static double calculateYIntercept(double slope, Point2D point) {
		return point.getY() - slope * point.getX();
	}
	private static List<Point2D> getLinePoints(Line line){
		Point2D a = new Point2D(line.getStartX(), line.getStartY());
		Point2D b = new Point2D(line.getEndX(), line.getEndY());
		ArrayList<Point2D> points = new ArrayList<>();
		points.add(a);
		points.add(b);
		return points;
	}
	private static Point2D getPoint(Line line, int whichPoint) {
		if (whichPoint != 1 && whichPoint != 2) {
			throw new IllegalArgumentException("Invalid point index. Only 1 and 2 are allowed.");
		}

		List<Point2D> linePoints = getLinePoints(line);
		return linePoints.get(whichPoint - 1);
	}
}
