module com.trek.customermanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.trek.customermanager to javafx.fxml;
    exports com.trek.customermanager;
}