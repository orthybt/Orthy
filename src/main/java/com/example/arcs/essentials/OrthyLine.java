package com.example.arcs.essentials;

import com.example.arcs.cloud.Cloud;
import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

/**
 * This is the core entity of the application
 *
 */
public class OrthyLine {
	/**
	 * The main class variables
	 */
	private Point2D a, b, midPoint, perpendicularStart;
	private Line line, perpendicularLine;
	private Double slope, yIntercept, negativeSlope, yInterceptPerpendicular;
	private boolean isLineInitialized;
	/**
	 * Default constructor
	 */
	public OrthyLine() {
		this.a = null;
		this.b = null;
		this.midPoint = null;
		this.perpendicularStart = null;
		this.line = null;
		this.perpendicularLine = null;
		this.slope = null;
		this.yIntercept = null;
		this.negativeSlope = null;
		this.yInterceptPerpendicular = null;
	}
	public OrthyLine(Point2D p1, Point2D p2) {
		this.a = p1;
		this.b = p2;
		this.midPoint = p1.midpoint(p2);
		this.line = new Line(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		this.slope = OrthyMath.calculateLineSlope(p1, p2);
		this.negativeSlope = OrthyMath.calculatePerpendicularLineSlope(slope);
		this.yIntercept = OrthyMath.calculateY_Intercept(slope, p2);
		this.yInterceptPerpendicular = OrthyMath.calculateY_Intercept(negativeSlope, midPoint);
		this.perpendicularStart = new Point2D(0, getyInterceptPerpendicular().intValue());
		this.perpendicularLine = new Line(getPerpendicularStart().getX(), getPerpendicularStart().getY(),
				getMidPoint().getX(), getMidPoint().getY());
	}
	public OrthyLine(Line line) {
		this(new Point2D(line.getStartX(), line.getStartY()), new Point2D(line.getEndX(), line.getEndY()));
	}
	/**
	 * Get me a line that is perpendicular to the current line at the destination
	 * @param destination
	 * @return
	 */
	public Line getPerpendicularTranslate(Point2D destination) {
		Point2D translatedPoint = OrthyMath.translateLineEndPoint(destination, perpendicularStart, midPoint);
		return new Line(destination.getX(), destination.getY(), translatedPoint.getX(), translatedPoint.getY());
	}
	/**
	 * Returns the length of the line in Pixels
	 * @return
	 */
	public Double getLineLength() {
		return a.distance(b);
	}
	public Double getLineLengthMM(){
		return getLineLength();
	}
	/**
	 * Getters and setters
	 */
	public Point2D getA() {
		return a;
	}
	public void setA(Point2D a) {
		this.a = a;
	}
	public Point2D getB() {
		return b;
	}
	public void setB(Point2D b) {
		this.b = b;
	}
	public Point2D getMidPoint() {
		return midPoint;
	}
	public void setMidPoint(Point2D midPoint) {
		this.midPoint = midPoint;
	}
	public Point2D getPerpendicularStart() {
		return perpendicularStart;
	}
	public void setPerpendicularStart(Point2D perpendicularStart) {
		this.perpendicularStart = perpendicularStart;
	}
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
	public Line getPerpendicularLine() {
		return perpendicularLine;
	}
	public void setPerpendicularLine(Line perpendicularLine) {
		this.perpendicularLine = perpendicularLine;
	}
	public Double getSlope() {
		return slope;
	}
	public void setSlope(Double slope) {
		this.slope = slope;
	}
	public Double getyIntercept() {
		return yIntercept;
	}
	public void setyIntercept(Double yIntercept) {
		this.yIntercept = yIntercept;
	}
	public Double getNegativeSlope() {
		return negativeSlope;
	}
	public void setNegativeSlope(Double negativeSlope) {
		this.negativeSlope = negativeSlope;
	}
	public Double getyInterceptPerpendicular() {
		return yInterceptPerpendicular;
	}
	public void setyInterceptPerpendicular(Double yInterceptPerpendicular) {
		this.yInterceptPerpendicular = yInterceptPerpendicular;
	}

	public boolean isLineInitialized() {
		return isLineInitialized;
	}
	public void setLineInitialized(boolean isLineInitialized) {
		this.isLineInitialized = isLineInitialized;
	}
	/**
	 * toString method
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("OrthyLine length: ").append(getLineLength()).append(", ");
		sb.append("Line Start: ").append(a).append(", ");
		sb.append("Line End: ").append(b).append(", ");
		sb.append("Midpoint: ").append(midPoint).append(", ");
		sb.append("Slope: ").append(slope).append(", ");
		sb.append("Y-Intercept: ").append(yIntercept).append(", ");
		sb.append("Negative Slope: ").append(negativeSlope).append(", ");
		sb.append("Y-Intercept (Perpendicular): \n").append(yInterceptPerpendicular);
		return sb.toString();
	}
}
