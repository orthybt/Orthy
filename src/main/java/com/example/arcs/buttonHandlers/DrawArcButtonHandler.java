package com.example.arcs.buttonHandlers;

import com.example.arcs.essentials.ArcHandler;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

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
	private TextArea textArea;

	public DrawArcButtonHandler(ArcHandler arcHandler, Pane drawingPane, TextArea textArea) {
		this.arcHandler = arcHandler;
		this.drawingPane = drawingPane;
		this.textArea = textArea;
	}

	@Override
	public void handle(MouseEvent event) {
		if (!arcHandler.getOrthyArc().isInit()) {
			// Here is where I give the point to the orthyArc point list
			// otherwise this condition will always be true
			arcHandler.selectPoints(event, drawingPane);
		}
		// If the arc has 2 points and thus is defined, create the arc
		if (arcHandler.getOrthyArc().isInit()) {
			arcHandler.createArc();
			arcHandler.drawArc(drawingPane);

			double length = arcHandler.getOrthyArc().getArcLengthMM();

			// Draw the length of the arc on the drawing pane
			Text lengthText = new Text(String.format("Arc length: %.1f mm", length));
			lengthText.setX(arcHandler.getOrthyArc().getCenterX());
			lengthText.setY(arcHandler.getOrthyArc().getCenterY());
			drawingPane.getChildren().add(lengthText);

			// Append the length of the arc to the TextArea
			textArea.appendText(String.format("Arc length: %.1f mm%n", length));
		}
	}
}
