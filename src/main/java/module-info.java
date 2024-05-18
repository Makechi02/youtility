module com.makechi.youtility {
	requires javafx.controls;
	requires javafx.fxml;


	opens com.makechi.youtility to javafx.fxml;
	exports com.makechi.youtility;
}