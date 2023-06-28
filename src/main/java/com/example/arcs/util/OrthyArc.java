package com.example.arcs.util;

import com.example.arcs.essentials.Printer;
import javafx.geometry.Point2D;
import javafx.scene.shape.Arc;

import java.util.ArrayList;
import java.util.List;

/**
 *OrthyArc
 * In the future it will contain basic arc, quad arc,
 * paraboles, etc...
 * Below is the base class
 */
public class OrthyArc {
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
	}//default constructor
	public void modifyRadiusX(double value){
		arc.setRadiusX(arc.getRadiusX()+value);
	}
	public void modifyRadiusY(double value){
		arc.setRadiusY(arc.getRadiusY()+value);
	}
	// Check if the arc is initialized with two points
	public boolean isInit(){
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
	//GETTERS AND SETTERS
	/**
	 * Extrapolated objects
	 * @return
	 */
	public Point2D getP1(){
		return arcPoints.get(0);
	}
	public Point2D getP2(){
		return arcPoints.get(1);
	}
	public void setP1(Point2D p){
		this.arcPoints.add(p);
	}
	public void setP2(Point2D p){
		this.arcPoints.add(p);
	}
	public void setArcPoints(List<Point2D> arcPoints){
		this.arcPoints.clear();
		this.arcPoints.addAll(arcPoints);
		Printer.printPoints(arcPoints);
	}
	public List<Point2D> getArcPoints(){
		return this.arcPoints;
	}
	public Arc getArc() {
		return arc;
	}
	public void setArc(Arc arc) {
		this.arc = arc;
	}

	@Override
	public String toString() {
		return "OrthyArc{" +
				"arcPoints=" + arcPoints +
				", arc=" + arc +
				'}';
	}
}
