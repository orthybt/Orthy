package com.example.arcs.buttonHandlers;

import com.example.arcs.essentials.LineHandler;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class DrawLineButtonHandler implements EventHandler<MouseEvent> {
	private LineHandler lineHandler;
	private Pane drawingPane;
	private TextArea textArea;

	public DrawLineButtonHandler(LineHandler lineHandler, Pane drawingPane, TextArea textArea) {
		this.lineHandler = lineHandler;
		this.drawingPane = drawingPane;
		this.textArea = textArea;
	}

	@Override
	public void handle(MouseEvent event) {
		if (!lineHandler.isLineInitialized()) {
			lineHandler.selectPoint(event, drawingPane);
		}
		if (lineHandler.isLineInitialized()) {
			lineHandler.createLine();
			lineHandler.drawLine(drawingPane);
			double length = lineHandler.getLine().getLineLengthMM();
			textArea.appendText(String.format("Line length: %.1f%n", length));
			lineHandler.resetLine();
		}
	}
}
