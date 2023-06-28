package tests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.stage.Stage;

public class CubicCurveDrawing extends Application {

	@Override
	public void start(Stage primaryStage) {
		Pane pane = new Pane();

		// Create a cubic curve
		CubicCurve cubicCurve = new CubicCurve();
		cubicCurve.setStartX(50);
		cubicCurve.setStartY(100);
		cubicCurve.setControlX1(150);
		cubicCurve.setControlY1(50);
		cubicCurve.setControlX2(250);
		cubicCurve.setControlY2(150);
		cubicCurve.setEndX(350);
		cubicCurve.setEndY(100);
		cubicCurve.setStroke(Color.BLACK);
		cubicCurve.setStrokeWidth(2);
		cubicCurve.setFill(Color.TRANSPARENT);

		pane.getChildren().add(cubicCurve);

		Scene scene = new Scene(pane, 400, 200);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
