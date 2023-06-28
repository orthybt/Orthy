package com.example.arcs;

import com.example.arcs.buttonHandlers.*;
import com.example.arcs.essentials.CalibrationHandler;
import com.example.arcs.essentials.LineHandler;
import com.example.arcs.essentials.ArcHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import java.io.File;
import java.nio.file.Paths;
import java.util.Optional;

public class OrthyController {
	/**
	 * Handlers
	 */
	CalibrationHandler calibrationHandler = new CalibrationHandler();
	ArcHandler arcHandler = new ArcHandler();
	LineHandler lineHandler = new LineHandler();
	/**
	 * Button handlers
	 */
	CalibrateButtonHandler calibrateButtonHandler;
	DrawLineButtonHandler drawLineButtonHandler;
	DrawArcButtonHandler drawArcButtonHandler;
	DragArcButtonHandler dragArcButtonHandler;
	RotateArcButtonHandler rotateArcButtonHandler;
	/**
	 * Gui elements
	 */
	@FXML
	private ImageView imageView;
	@FXML
	private Button loadImageButton;
	@FXML
	private Button calibrateButton;
	@FXML
	private Button resetCalibrationButton;
	@FXML
	private Button drawArcButton;
	@FXML
	private Button dragArcButton;
	@FXML
	private Button rotateArcButton;
	@FXML
	private Button increaseArcWidthButton;
	@FXML
	private Button decreaseArcWidthButton;
	@FXML
	private Button increaseArcHeightButton;
	@FXML
	private Button decreaseArcHeightButton;
	@FXML
	private Button drawLineButton;
	@FXML
	private Button clearButton;
	@FXML
	private StackPane stackPane;
	@FXML
	private Pane imagePane;
	@FXML
	private Pane drawingPane;
	@FXML
	private TextArea textArea;

	/**
	 * The main method that initialize the controller class and assigns the button handlers.
	 * And all the other initialization logic.
	 */
	@FXML
	private void initialize() {
		initStackPane();
		/**
		 * Initialize button handlers
		 */
		calibrateButtonHandler = new CalibrateButtonHandler(lineHandler, drawingPane);
		drawLineButtonHandler = new DrawLineButtonHandler(lineHandler, drawingPane);
		drawArcButtonHandler = new DrawArcButtonHandler(arcHandler, drawingPane);
		dragArcButtonHandler = new DragArcButtonHandler(arcHandler, drawingPane);
		rotateArcButtonHandler = new RotateArcButtonHandler(arcHandler, drawingPane);
		/**
		 * Assign functions to each button
		 */
		loadImageButton.setOnAction(event -> loadImage());
		calibrateButton.setOnAction(event -> calibrate());
		resetCalibrationButton.setOnAction(event -> resetCalibration());
		drawLineButton.setOnAction(event -> drawLine());
		drawArcButton.setOnAction(event -> drawArc());
		dragArcButton.setOnAction(event -> dragArc());
		rotateArcButton.setOnAction(event -> rotateArc());
		increaseArcWidthButton.setOnAction(event -> increaseArcWidth());
		decreaseArcWidthButton.setOnAction(event -> decreaseArcWidth());
		increaseArcHeightButton.setOnAction(event -> increaseArcHeight());
		decreaseArcHeightButton.setOnAction(event -> decreaseArcHeight());
		clearButton.setOnAction(event -> handleClearButton());
	}

	/**
	 * The main method that initialize the controller class and assigns the button handlers.
	 * And all the other initialization logic.
	 */
	@FXML
	private void initStackPane() {
		if (!stackPane.getChildren().contains(imagePane)) {
			stackPane.getChildren().add(0, imagePane);
		}

		if (!stackPane.getChildren().contains(drawingPane)) {
			stackPane.getChildren().add(1, drawingPane);
		}
	}

	@FXML
	private void loadImage() {
		FileChooser fileChooser = new FileChooser();

		// Set the initial directory
		fileChooser.setInitialDirectory(new File("C:\\Users\\Orthy\\Documents"));

		// Add file filters for images
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"), new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"), new FileChooser.ExtensionFilter("BMP", "*.bmp"), new FileChooser.ExtensionFilter("GIF", "*.gif"));

		// Open the file dialog
		File file = fileChooser.showOpenDialog(null);

		if (file != null) {
			// Load and display the image
			Image image = new Image(Paths.get(file.getAbsolutePath()).toUri().toString());
			imageView.setImage(image);
		}
	}

	@FXML
	private void calibrate() {
		//remove the previous mouse event handlers from the drawingPane
		drawingPane.setOnMouseClicked(null);
		// Set the calibrateButtonHandler on the drawingPane
		drawingPane.setOnMouseClicked(this::handleCalibrateButton);
	}

	@FXML
	private void resetCalibration() {
		handleResetCalibrationButton();
	}

	@FXML
	private void drawLine() {
		//remove the previous mouse event handlers from the drawingPane
		drawingPane.setOnMouseClicked(null);
		// Set the drawLineButtonHandler on the drawingPane
		drawingPane.setOnMouseClicked(this::handleDrawLineButton);
	}

	@FXML
	private void drawArc() {
		drawingPane.setOnMouseClicked(this::handleDrawArcButton);
	}

	@FXML
	private void dragArc() {
		//remove the previous mouse event handlers from the drawingPane
		drawingPane.setOnMouseClicked(null);
		// Set the dragArcButtonHandler on the drawingPane
		drawingPane.setOnMouseClicked(this::handleDragArcButton);
	}

	@FXML
	private void rotateArc() {
		drawingPane.setOnMouseClicked(this::handleRotateArcButton);
	}

	@FXML
	private void increaseArcWidth() {
		handleIncreaseWidthButton();
	}

	@FXML
	private void decreaseArcWidth() {
		handleDecreaseWidthButton();
	}

	@FXML
	private void increaseArcHeight() {
		handleIncreaseHeightButton();
	}

	@FXML
	private void decreaseArcHeight() {
		handleDecreaseHeightButton();
	}

	//Method initialization
	private void handleCalibrateButton(MouseEvent event) {
		calibrateButtonHandler.handle(event);
		if (lineHandler.isCalibrationInitialized()) {
			textArea.appendText("Calibration points set\n");
			textArea.appendText("Calibration succesfull: \n");
		}
	}

	private void handleResetCalibrationButton() {
		calibrateButtonHandler.resetCalibration();
		textArea.appendText("Calibration points reset\n");
	}

	private void handleDrawLineButton(MouseEvent event) {
		if (!lineHandler.isLineInitialized()) {
			lineHandler.selectPoint(event, drawingPane);
		}
		if (lineHandler.isLineInitialized()) {
			lineHandler.createLine();
			lineHandler.drawLine(drawingPane);
			// append the length of the line to the TextArea
			double length = lineHandler.getLine().getLineLengthMM(); // assuming getLengthOfLine()
			// returns the length of the line
			textArea.appendText(String.format("Line length: %.1f%n", length));
			lineHandler.resetLine();
		}
	}

	private void handleDrawArcButton(MouseEvent event) {
		drawArcButtonHandler.handle(event);
	}

	private void handleDragArcButton(MouseEvent event) {
		dragArcButtonHandler.handle(event);
	}

	private void handleRotateArcButton(MouseEvent event) {
		rotateArcButtonHandler.handle(event);
	}

	private void handleIncreaseWidthButton() {
		arcHandler.getTempArc().modifyRadiusX(5);
	}

	private void handleDecreaseWidthButton() {
		arcHandler.getTempArc().modifyRadiusX(-5);
	}

	private void handleIncreaseHeightButton() {
		arcHandler.getTempArc().modifyRadiusY(5);
	}

	private void handleDecreaseHeightButton() {
		arcHandler.getTempArc().modifyRadiusY(-5);
	}

	private void handleClearButton() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Clear Confirmation");
		alert.setContentText("Do you want to clear the text area as well?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			// clear the text area as well
			textArea.clear();
		}
		drawingPane.getChildren().clear();
	}
}
