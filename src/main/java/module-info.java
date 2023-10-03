module es.QuePasApp {
    requires javafx.controls;
	requires javafx.fxml;
    exports es.QuePasApp;
    exports controllers to javafx.fxml;
    opens controllers to javafx.fxml;
}
