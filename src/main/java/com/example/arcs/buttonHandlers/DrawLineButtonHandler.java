package com.example.arcs.buttonHandlers;

import com.example.arcs.essentials.LineHandler;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class DrawLineButtonHandler implements EventHandler<MouseEvent> {
	/**
	 * This class is responsible for handling the precisionLine button click
	 * event.
	 */
	private LineHandler lineHandler;
	private Pane drawingPane;
	/**
	 * Constructor
	 * @param lineHandler
	 * @param drawingPane
	 */
	public DrawLineButtonHandler(LineHandler lineHandler, Pane drawingPane) {
		this.lineHandler = lineHandler;
		this.drawingPane = drawingPane;
	}
	/**
	 * This method handles the calibrate button click event.
	 * @param event the event which occurred
	 */
	@Override
	public void handle(MouseEvent event) {
		if (!lineHandler.isLineInitialized()) {
			lineHandler.selectPoint(event, drawingPane);
		}
		if (lineHandler.isLineInitialized()) {
			lineHandler.createLine();
			lineHandler.drawLine(drawingPane);
			lineHandler.resetLine();
		}
	}
}
