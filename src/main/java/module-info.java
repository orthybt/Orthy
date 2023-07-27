module com.example.arcs {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires com.opencsv;


	opens com.example.arcs to javafx.fxml;
	exports com.example.arcs;
	exports tests;
}
