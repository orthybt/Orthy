package com.example.arcs;

import com.example.arcs.buttonHandlers.*;
import com.example.arcs.essentials.CalibrationHandler;
import com.example.arcs.cloud.Cloud;
import com.example.arcs.essentials.LineHandler;
import com.example.arcs.essentials.ArcHandler;
import com.example.arcs.recycleBin.PointHandler;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;

import static com.example.arcs.tests.OrthoBiomechanics.*;

public class OrthyController {
	/**
	 * Cloud access
	 */
	private Cloud c = Cloud.getInstance();

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
	private Pane mainPane;
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
	private Button calculateMovementButton;
	@FXML
	private TextField boltonUpperSumTextField;
	@FXML
	private TextField boltonLowerSumTextField;
	@FXML
	private TextField boltonUpperAntSumTextField;
	@FXML
	private TextField boltonLowerAntSumTextField;
	@FXML
	private Button calculateBoltonButton;
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
	@FXML
	private TextField blValueTextField;
	@FXML
	private TextField mdValueTextField;


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
		calibrateButtonHandler = new CalibrateButtonHandler(c.getCalibrationHandler(), drawingPane,
				textArea);
		drawLineButtonHandler =
				new DrawLineButtonHandler( drawingPane, textArea);
		drawArcButtonHandler = new DrawArcButtonHandler( drawingPane, textArea);
		dragArcButtonHandler = new DragArcButtonHandler(c.getArcHandler(), drawingPane);
		rotateArcButtonHandler = new RotateArcButtonHandler(c.getArcHandler(), drawingPane);
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
		calculateMovementButton.setOnAction(event -> calculateMovement());
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
		mainPane.setOnKeyPressed(event -> {
			if (event.getCode() == javafx.scene.input.KeyCode.ENTER) {
				calculateMovement();
				blValueTextField.requestFocus();
			} else if (event.getCode() == javafx.scene.input.KeyCode.ESCAPE) {
				handleClearButton();
			}
		});
		mainPane.setFocusTraversable(true);
	}
	@FXML
	private void loadImage() {
		FileChooser fileChooser = new FileChooser();

		// Set the initial directory
		fileChooser.setInitialDirectory(new File("C:\\Users\\User\\Desktop" +
				"\\Orthy\\SS\\Images"
				));

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
	@FXML
	private void calculateMovement() {
		handleCalculateMovementButton();
	}
	/**
	 * Calibration Handlers
	 */
	private void handleCalibrateButton(MouseEvent event) {
		calibrateButtonHandler.handle(event);
	}
	private void handleResetCalibrationButton() {
		calibrateButtonHandler.resetCalibration();
	}
	/**
	 * Line Handlers
	 */
	private void handleDrawLineButton(MouseEvent event) {
		drawLineButtonHandler.handle(event);
	}
	/**
	 * Arc Handlers
	 */
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
		c.getArcHandler().getOrthyArc().modifyRadiusX(5);
	}
	private void handleDecreaseWidthButton() {
		c.getArcHandler().getOrthyArc().modifyRadiusX(-5);
	}
	private void handleIncreaseHeightButton() {
		c.getArcHandler().getOrthyArc().modifyRadiusY(5);
	}
	private void handleDecreaseHeightButton() {
		c.getArcHandler().getOrthyArc().modifyRadiusY(-5);
	}
	/**
	 * Calculate Movement Handlers
	 */
	private void handleCalculateMovementButton() {
		// Check if text fields are empty
		if (blValueTextField.getText().trim().isEmpty() || mdValueTextField.getText().trim().isEmpty()) {
			showAlert("Input Error", "Please enter values before calculating.");
			return; // Stop further execution of this method
		}
		// Check if text fields contain valid numbers
		try {
			Double.parseDouble(blValueTextField.getText().trim());
			Double.parseDouble(mdValueTextField.getText().trim());
		} catch (NumberFormatException e) {
			showAlert("Input Error", "Please enter valid numbers before calculating.");
			return; // Stop further execution of this method
		}

		double blValue = Double.parseDouble(blValueTextField.getText());
		double mdValue = Double.parseDouble(mdValueTextField.getText());
		//calculate the diagonal movement
		double diagonalMovement = Math.sqrt(blValue * blValue + mdValue * mdValue);
		StringBuilder results = new StringBuilder();
		// Format the diagonalMovement to 2 decimal places
		results.append("Diagonal movement: ").append(String.format("%.2f", diagonalMovement)).append(" mm\n");
		if (diagonalMovement > 1.25 * blValue) {
			results.append("Adjustments needed for BL and MD values.\n");
		} else {
			results.append("No adjustments needed for BL and MD values.\n");
		}
		// Calculate minimum steps
		int steps = 1;
		while (!isValidStep(blValue, mdValue, steps) && steps < 100) {  // Assuming max steps as 100
			steps++;
		}
		if (steps < 100) {
			results.append("Minimum steps required: ").append(steps).append("\n");
			double adjustedBL = modifyBL(blValue, mdValue, steps);
			double adjustedMD = modifyMD(blValue, mdValue, steps);
			results.append("Adjusted BL value: ").append(String.format("%.2f", adjustedBL)).append(" mm\n");
			results.append("Adjusted MD value: ").append(String.format("%.2f", adjustedMD)).append(" mm\n");

		} else {
			results.append("Unable to find a suitable number of steps under the current constraints.\n");
		}
		textArea.setText(results.toString());
		textArea.wrapTextProperty().setValue(true);
	}
	/**
	 * Clear Button Handler
	 */
	// TODO: 7/12/2023 escape key to clear the drawingPane
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
	/**
	 * Helper methods
	 */
	private void showAlert(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}
}
