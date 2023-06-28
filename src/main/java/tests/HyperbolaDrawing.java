package tests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.CubicCurveTo;
import javafx.stage.Stage;

public class HyperbolaDrawing extends Application {

	@Override
	public void start(Stage primaryStage) {
		double startPointX = 100;   // X coordinate of the start point
		double startPointY = 200;   // Y coordinate of the start point
		double heightPointX = 300;  // X coordinate of the height point
		double heightPointY = 100;  // Y coordinate of the height point

		// Calculate the center of the hyperbola
		double centerX = (startPointX + heightPointX) / 2;
		double centerY = (startPointY + heightPointY) / 2;

		// Calculate the distance between the center and the height point
		double distance = Math.sqrt(Math.pow(centerX - heightPointX, 2) + Math.pow(centerY - heightPointY, 2));

		// Calculate the horizontal and vertical radii of the hyperbola
		double a = distance;
		double b = Math.sqrt(Math.pow(centerX - startPointX, 2) + Math.pow(centerY - startPointY, 2));

		Pane pane = new Pane();
		Path path = new Path();
		path.getElements().add(new MoveTo(startPointX, startPointY));

		// Generate points along the hyperbola curve
		for (double t = 0; t < 2 * Math.PI; t += 0.01) {
			double x = centerX + a * Math.cos(t);
			double y = centerY + b * Math.sin(t);
			path.getElements().add(new CubicCurveTo(x, y, x, y, x, y));
		}

		pane.getChildren().add(path);

		Scene scene = new Scene(pane, 400, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
