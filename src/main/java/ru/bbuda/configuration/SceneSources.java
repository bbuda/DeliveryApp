package ru.bbuda.configuration;

import lombok.Getter;

@Getter
public enum SceneSources {
    PERSONAL_ACCOUNT("/view/ personal-account.fxml"),
    HELLO_VIEW("/view/hello-view.fxml"),
    REGIST("/view/regist.fxml"),
    REGISTRATION_PARCEL("/view/registrationParcel.fxml");

    private SceneSources(String src) {
        this.src = src;
    }

    private final String src;

}
