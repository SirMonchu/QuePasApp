module es.QuePasApp {
    requires javafx.controls;
	requires javafx.fxml;
    exports es.QuePasApp;
    exports es.QuePasApp.controllers to javafx.fxml;
    opens es.QuePasApp.controllers to javafx.fxml;
}
