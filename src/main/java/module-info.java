module com.app.panfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.app.panfx to javafx.fxml;
    exports com.app.panfx;
}