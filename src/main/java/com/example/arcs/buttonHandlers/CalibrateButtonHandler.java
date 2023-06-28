package com.example.arcs.buttonHandlers;

import com.example.arcs.essentials.LineHandler;
import com.example.arcs.essentials.Printer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class CalibrateButtonHandler implements EventHandler<MouseEvent> {
	/**
	 * This class is responsible for handling the calibrate button click event.
	 */
	private LineHandler lineHandler;
	private Pane drawingPane;
	/**
	 * Constructor
	 * @param lineHandler
	 * @param drawingPane
	 */
	public CalibrateButtonHandler(LineHandler lineHandler, Pane drawingPane) {
		this.lineHandler = lineHandler;
		this.drawingPane = drawingPane;
	}
	/**
	 * This method handles the calibrate button click event.
	 * @param event the event which occurred
	 */
	@Override
	public void handle(MouseEvent event) {
		if (!lineHandler.isCalibrationInitialized()) {
			lineHandler.selectCalibrationPoint(event, drawingPane);
		}
		if (lineHandler.isCalibrationInitialized()) {
			Printer.print("Calibration is initialized");
		}
	}
	public void resetCalibration() {
		lineHandler.resetCalibration();
	}
}
