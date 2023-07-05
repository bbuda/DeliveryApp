module DeliveryApp.main {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires lombok;

    opens ru.bbuda to javafx.fxml;
    exports ru.bbuda;

    opens ru.bbuda.controller to javafx.fxml;
    exports ru.bbuda.controller;
}