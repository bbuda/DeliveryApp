module DeliveryApp.main {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.bbuda to javafx.fxml;
    exports ru.bbuda;
}