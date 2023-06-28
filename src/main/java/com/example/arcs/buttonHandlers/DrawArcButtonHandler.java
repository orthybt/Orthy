package com.example.arcs.buttonHandlers;

import com.example.arcs.essentials.ArcHandler;
import javafx.event.EventHandler;
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

	private ArcHandler arcHandler;
	private Pane drawingPane;

	public DrawArcButtonHandler(ArcHandler arcHandler, Pane drawingPane) {
		this.arcHandler = arcHandler;
		this.drawingPane = drawingPane;
	}

	@Override
	public void handle(MouseEvent event) {
		if (!arcHandler.getOrthyArc().isInit()) {
			/**
			 * Here is where i give the point to the orthyArc point list
			 * otherwise this condition will always be true
			 */
			arcHandler.handleMouseClick(event, drawingPane);
		}
		//if the arc has 2 points and thus is defined
		//create the arc
		if (arcHandler.getOrthyArc().isInit()) {
			arcHandler.createArc();
			arcHandler.drawArc(drawingPane);
		}
	}
}
