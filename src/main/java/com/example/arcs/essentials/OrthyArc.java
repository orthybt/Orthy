package com.example.arcs.essentials;

import com.example.arcs.cloud.Cloud;
import javafx.geometry.Point2D;
import javafx.scene.shape.Arc;

import java.util.ArrayList;
import java.util.List;

/**
 * You can hame many arcs so cloud access is not needed
 */
public class OrthyArc {
	//
	private List<Point2D> arcPoints;
	private Arc arc;
	private boolean isArcInitialized;

	public OrthyArc(){
		this.arcPoints = new ArrayList<>();
		this.arc = new Arc();
		this.isArcInitialized = false;
	}

	public Double getArcLength(){
		return arc.getLength();
	}

	public void modifyRadiusX(double value){
		arc.setRadiusX(arc.getRadiusX()+value);
	}
	public void modifyRadiusY(double value){
		arc.setRadiusY(arc.getRadiusY()+value);
	}

	public void resetArcPoints(){
		this.arcPoints.clear();
	}


	public void setStartPoint(Point2D startPoint){
		arcPoints.add(startPoint);
	}
	public void setEndPoint(Point2D endPoint){
		arcPoints.add(endPoint);
	}
	public void setArcInitialized(boolean arcInitialized) {
		isArcInitialized = arcInitialized;
	}
	public boolean isArcInitialized(){
		return isArcInitialized;
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
