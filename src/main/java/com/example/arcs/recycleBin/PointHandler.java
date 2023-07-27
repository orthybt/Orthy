package com.example.arcs.recycleBin;

import com.example.arcs.cloud.Cloud;
import com.example.arcs.essentials.ArcFactory;
import com.example.arcs.essentials.LineFactory;
import com.example.arcs.essentials.OrthyArc;
import com.example.arcs.essentials.OrthyLine;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.List;

/**
 * This a basic pointHandle
 * It can be upgraded to do many things. For example, for arc selection and
 * drawting, it uses pointSelector to select points and the pen to draw them,
 * all of this wrapped in amethod called selectArcPoints()
 */
public class PointHandler{
	private PointSelector pointSelector;
	private Pen pen;

	public PointHandler() {
		this.pointSelector = new PointSelector();
		this.pen = new Pen();
	}

	/**
	 * Arc Methods
	 */
	public void selectArcPoints(MouseEvent event, Pane drawingPane){
		pointSelector.handle(event);
		pen.drawPoint(new Point2D(event.getX(), event.getY()), drawingPane);
		if(getArcPoints().size() == 2){
			createArc();
			pointSelector.resetPointSelector();
		}
	}
	public void createArc(){
		Point2D startPoint = getArcPoints().get(0);
		Point2D endPoint = getArcPoints().get(1);
		OrthyArc arc = ArcFactory.createOrthyArc(startPoint, endPoint);
		Cloud.getInstance().getArcHandler().setArcPoints(getArcPoints());
		Cloud.getInstance().getArcHandler().setOrthyArc(arc);
	}
	public List<Point2D> getArcPoints(){
		return pointSelector.getPoints();
	}

	/**
	 * Line Methods
	 */
	public void selectLinePoints(MouseEvent event, Pane drawingPane){
		pointSelector.handle(event);
			pen.drawPoint(new Point2D(event.getX(), event.getY()), drawingPane);
			if(getLinePoints().size() == 2){
				createLine();
				pointSelector.resetPointSelector();
			}
		}
		public void createLine(){
			Point2D startPoint = getLinePoints().get(0);
			Point2D endPoint = getLinePoints().get(1);
			OrthyLine line = LineFactory.createOrthyLine(startPoint, endPoint);
			Cloud.getInstance().getLineHandler().setLinePoints(getLinePoints());
			Cloud.getInstance().getLineHandler().setOrthyLine(line);

		}
		public List<Point2D> getLinePoints(){
			return pointSelector.getPoints();
	}
	/**
	 * Calibrate Methods
	 */
	public void selectCalibratePoints(MouseEvent event, Pane drawingPane){
		pointSelector.handle(event);
		pen.drawPoint(new Point2D(event.getX(), event.getY()), drawingPane);
		if(getCalibratePoints().size() == 2){
			Cloud.getInstance().getCalibrationHandler().getCalibrationPoints().addAll(getCalibratePoints());
			Cloud.getInstance().getCalibrationHandler().calibrate();
			Cloud.getInstance().getCalibrationHandler().setCalibrationInitialized(true);
			pointSelector.resetPointSelector();
		}
	}
	public List<Point2D> getCalibratePoints(){
		return pointSelector.getPoints();
	}

	public PointSelector getPointSelector() {
		return pointSelector;
	}
}
