package com.example.arcs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ArcsApp extends Application {
	private String guiPath = "Orthy.fxml";

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(ArcsApp.class.getResource(guiPath));

		Parent root = new Pane();
		fxmlLoader.setRoot(root);

		fxmlLoader.load();

		Scene scene = new Scene(root);

		stage.setTitle("Orthy!");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
