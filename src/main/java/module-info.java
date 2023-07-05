module DeliveryApp.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.se;

    requires org.hibernate.orm.core;
    requires org.jboss.logging;
    requires jakarta.persistence;
    requires jakarta.transaction;
    requires jakarta.cdi;
    requires lombok;

    opens ru.bbuda to javafx.fxml;
    exports ru.bbuda;

    opens ru.bbuda.controller to javafx.fxml;
    exports ru.bbuda.controller;
}