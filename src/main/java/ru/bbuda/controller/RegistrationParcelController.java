package ru.bbuda.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationParcelController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ParcelBack;

    @FXML
    private Button ParcelGenerateId;

    @FXML
    private CheckBox ParcelIsUrgentDelivery;

    @FXML
    private TextField ParcelNameRecipient;

    @FXML
    private TextField ParcelPhoneRecipient;

    @FXML
    private PasswordField ParcelSurnameRecipient;

    @FXML
    private TextField ParcelWeight;

    @FXML
    void initialize() {


    }

}
