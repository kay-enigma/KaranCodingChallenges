module MyFirstProject {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.desktop;
	requires junit;
	
	opens application to javafx.graphics, javafx.fxml;
}
