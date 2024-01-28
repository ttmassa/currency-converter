module com.currency {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.web;

    opens com.currency to javafx.fxml;
    exports com.currency;
}
