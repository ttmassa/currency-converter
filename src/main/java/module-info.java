module com.currency {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.currency to javafx.fxml;
    exports com.currency;
}
