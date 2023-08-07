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
	private double lineLength = 0;
	private boolean isLineInitialized = false;

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
			isLineInitialized = true;
		}
		if (Cloud.getInstance().getCalibrationHandler().isCalibrationInitialized() &&
				isLineInitialized) {
			lineLength =
					Cloud.getInstance().getLineHandler().getLineLengthTemp();
			sum = sum + lineLength;
			textArea.setText(String.format("Line length: %.1f" + " mm"+"\n" +
					"Total length: %.1f" + " mm", lineLength, sum));
		}
		Cloud.getInstance().getLineHandler().setLineLengthTemp(0);
		isLineInitialized = false;
	}

	//Getters and setters

	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public double getLineLength() {
		return lineLength;
	}
	public void setLineLength(double lineLength) {
		this.lineLength = lineLength;
	}
}
