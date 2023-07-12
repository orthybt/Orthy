package com.example.arcs.essentials;

import javafx.geometry.Point2D;
import javafx.scene.shape.Arc;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a persistent class that does not go out of scope
 */
public class OrthyArc {
	/**
	 * Cloud access
	 */
	private Cloud c = Cloud.getInstance();
	/**
	 * Basic objects
	 */
	private List<Point2D> arcPoints;
	private Arc arc;
	/**
	 * Default constructor
	 */
	public OrthyArc(){
		this.arcPoints = new ArrayList<>();
		this.arc = new Arc();
	}
	/**
	 * Constructor with two points
	 * @param p1
	 * @param p2
	 */
	public OrthyArc(Point2D p1, Point2D p2){
		this.arcPoints = new ArrayList<>();
		this.arc = new Arc();
		this.arcPoints.add(p1);
		this.arcPoints.add(p2);
	}
	/**
	 * Constructor with an arc and derived points
	 * @param arc
	 */
	public OrthyArc(Arc arc){
		this.arcPoints = new ArrayList<>();
		this.arc = arc;
		this.arcPoints.add(new Point2D(arc.getCenterX(), arc.getCenterY()));
		this.arcPoints.add(new Point2D(arc.getCenterX()+arc.getRadiusX(), arc.getCenterY()));
	}
	/**
	 * The Length of the arc in pixels
	 */
	public Double getArcLength(){
		return arc.getLength();
	}
	/**
	 * The Length of the arc in mm
	 */
	public Double getArcLengthMM(){
		return arc.getLength()/c.getCalibrationHandler().getCalibrationFactor();
	}
	/**
	 * Make the arc wider or narrower
	 */
	public void modifyRadiusX(double value){
		arc.setRadiusX(arc.getRadiusX()+value);
	}
	/**
	 * Make the arc taller or shorter
	 */
	public void modifyRadiusY(double value){
		arc.setRadiusY(arc.getRadiusY()+value);
	}
	// Check if the arc is initialized with two points
	public boolean isArcInitialized(){
		boolean isInit;
		if (arcPoints.size() == 2){
			isInit = true;
		}
		else {
			isInit = false;
		}
		return isInit;
	}
	/**
	 * Reset the arc
	 */
	public void resetArcPoints(){
		this.arcPoints.clear();
	}
	/**GETTERS AND SETTERS*/
	public Point2D getP1(){
		return arcPoints.get(0);
	}
	public Point2D getP2(){
		return arcPoints.get(1);
	}
	public List<Point2D> getArcPoints() {
		return arcPoints;
	}
	public void setArcPoints(List<Point2D> arcPoints) {
		this.arcPoints = arcPoints;
	}
	public Arc getArc() {
		return arc;
	}
	public void setArc(Arc arc) {
		this.arc = arc;
	}
	/**OVERRIDES*/
	@Override
	public String toString() {
		return "OrthyArc{" +
				"arcPoints=" + arcPoints +
				", arc=" + arc +
				'}';
	}
}
