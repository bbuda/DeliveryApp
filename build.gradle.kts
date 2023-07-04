plugins {
    id("java")
}

group = "ru.com"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.hibernate:hibernate-core:6.2.6.Final")
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("org.openjfx:javafx-controls:19")
    implementation("org.openjfx:javafx-fxml:19")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}