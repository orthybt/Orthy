package com.example.arcs.buttonHandlers;

import com.example.arcs.essentials.CalibrationHandler;
import com.example.arcs.essentials.LineHandler;
import com.example.arcs.essentials.Printer;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class CalibrateButtonHandler implements EventHandler<MouseEvent> {
	/**
	 * This class is responsible for handling the calibrate button click event.
	 */
	private CalibrationHandler calibrationHandler;
	private Pane drawingPane;
	private TextArea textArea;
	/**
	 * Constructor
	 * @param calibrationHandler
	 * @param drawingPane
	 */
	public CalibrateButtonHandler(CalibrationHandler calibrationHandler,
	                              Pane drawingPane, TextArea textArea) {
		this.calibrationHandler = calibrationHandler;
		this.drawingPane = drawingPane;
		this.textArea = textArea;
	}
	/**
	 * This method handles the calibrate button click event.
	 * @param event the event which occurred
	 */
	@Override
	public void handle(MouseEvent event) {
		if (!calibrationHandler.isCalibrationInitialized()) {
			calibrationHandler.selectPoint(event, drawingPane);
		}
		if (calibrationHandler.isCalibrationInitialized()) {
			calibrationHandler.calibrate();
			textArea.setText("Calibration is successful");
		}
	}
	public void resetCalibration() {
		calibrationHandler.resetCalibration();
	}
}
