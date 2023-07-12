package com.example.arcs.buttonHandlers;

import com.example.arcs.essentials.CalibrationHandler;
import com.example.arcs.essentials.LineHandler;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class DrawLineButtonHandler implements EventHandler<MouseEvent> {
	private CalibrationHandler calibrationHandler;
	private LineHandler lineHandler;
	private Pane drawingPane;
	private TextArea textArea;

	public DrawLineButtonHandler(CalibrationHandler calibrationHandler,
	                             LineHandler lineHandler,
	                             Pane drawingPane,
	                             TextArea textArea) {
		this.calibrationHandler = calibrationHandler;
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
			try {
				lineHandler.createLine();
				lineHandler.drawLine(drawingPane);
				double length = lineHandler.getLine().getLineLengthMM();
				// Display line length in the TextArea
				textArea.appendText(String.format("Line length: %.1f mm%n", length));
				// Reset the line
				lineHandler.resetLine();
			} catch (Exception e) {
				// Exception occurred, show an error message
				Alert errorAlert = new Alert(Alert.AlertType.ERROR);
				errorAlert.setTitle("Error");
				errorAlert.setHeaderText("Measurement Error");
				errorAlert.setContentText("An error occurred during measurement.\n\n" + "Do you want to continue drawing without measurements, or calibrate first?");
				ButtonType continueButton = new ButtonType("Continue Drawing");
				ButtonType calibrateButton = new ButtonType("Calibrate");

				errorAlert.getButtonTypes().setAll(continueButton, calibrateButton);
				errorAlert.showAndWait().ifPresent(buttonType -> {
					if (buttonType == continueButton) {
						// User chose to continue drawing without measurements
						lineHandler.resetLine();
					} else if (buttonType == calibrateButton) {
						// User chose to calibrate, reset the line and clear the drawingPane
						lineHandler.resetLine();
						drawingPane.getChildren().clear();
						if(!calibrationHandler.isCalibrationInitialized()) {
							calibrationHandler.selectPoint(event, drawingPane);
						}
						if (calibrationHandler.isCalibrationInitialized()) {
							calibrationHandler.calibrate();
							errorAlert.close(); // Close the error alert after successful calibration
						}
					}
				});
			}
		}
	}
}
