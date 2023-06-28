package tests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.stage.Stage;

public class DentalArchCurve extends Application {

	@Override
	public void start(Stage primaryStage) {
		Pane pane = new Pane();

		// Create a cubic curve resembling a dental arch
		CubicCurve cubicCurve = new CubicCurve();
		cubicCurve.setStartX(50);
		cubicCurve.setStartY(200);
		cubicCurve.setControlX1(50);
		cubicCurve.setControlY1(50);
		cubicCurve.setControlX2(250);
		cubicCurve.setControlY2(50);
		cubicCurve.setEndX(250);
		cubicCurve.setEndY(200);
		cubicCurve.setStroke(Color.BLACK);
		cubicCurve.setStrokeWidth(2);
		cubicCurve.setFill(Color.TRANSPARENT);

		pane.getChildren().add(cubicCurve);

		Scene scene = new Scene(pane, 300, 250);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
