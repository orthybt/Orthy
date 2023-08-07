package com.example.arcs.buttonHandlers;

import com.example.arcs.cloud.Cloud;
import com.example.arcs.essentials.CalibrationHandler;
import com.example.arcs.essentials.LineHandler;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class DrawLineButtonHandler implements EventHandler<MouseEvent> {
	private Pane drawingPane;
	private TextArea textArea;
	private double sum = 0; // sum of all line lengths

	public DrawLineButtonHandler(Pane drawingPane, TextArea textArea) {
		this.drawingPane = drawingPane;
		this.textArea = textArea;
	}

	@Override
	public void handle(MouseEvent event) {
		if (!Cloud.getInstance().getLineHandler().isLineInitialized()) {
			Cloud.getInstance().getPointHandler().selectLinePoints(event, drawingPane);
		}
		if (Cloud.getInstance().getLineHandler().isLineInitialized()) {
			Cloud.getInstance().getLineHandler().drawLine(drawingPane);
		}
		if (Cloud.getInstance().getCalibrationHandler().isCalibrationInitialized()){
			double lineLength = Cloud.getInstance().getLineHandler().getLineLengthTemp();
			sum  = sum + lineLength;
			String lineLengthString = String.format("%.1f", lineLength);
			textArea.setText("Line length: " + lineLengthString + " mm"+"\n" + "Total length: %.1f" + sum + " mm");
		}
	}
}
