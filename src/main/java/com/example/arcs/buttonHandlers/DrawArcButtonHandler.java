package com.example.arcs.buttonHandlers;

import com.example.arcs.essentials.ArcHandler;
import com.example.arcs.cloud.Cloud;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * A handler class that is specific to a button
 * It contains all the objects it needs to complete the
 * method call. And everything is wrapped in the handle()
 * method
 * Basically the Controller method handleDrawArcButton,
 * wraps the call to DrawArcButtonHandler.handle(), which in turn
 * is wrapped by drawArc() method, which is passed to
 * setOnMouseCLicked of the DrawingPane
 */
public class DrawArcButtonHandler implements EventHandler<MouseEvent> {
	private Pane drawingPane;
	private TextArea textArea;

	public DrawArcButtonHandler(Pane drawingPane, TextArea textArea) {
		this.drawingPane = drawingPane;
		this.textArea = textArea;
	}

	@Override
	public void handle(MouseEvent event) {

		if (!Cloud.getInstance().getArcHandler().isArcInitialized()) {
			Cloud.getInstance().getPointHandler().selectArcPoints(event, drawingPane);
		}
		if (Cloud.getInstance().getArcHandler().isArcInitialized()) {
			Cloud.getInstance().getArcHandler().drawArc(drawingPane);
			Cloud.getInstance().getArcHandler().resetArc();

//			double length = arcHandler.getOrthyArc().getArcLength();
//			double lenMM = length/c.getCalibrationHandler().getCalibrationFactor();
//			textArea.appendText(String.format("Arc length: %.1f mm%n", lenMM));
		}
	}
}
