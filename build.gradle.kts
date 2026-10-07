plugins {
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "com.trek"
version = "1.0"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

javafx {
    version = "21.0.8"
    modules = listOf("javafx.controls", "javafx.fxml")
}

application {
    mainModule.set("com.trek.customermanager")
    mainClass.set("com.trek.customermanager.HelloApplication")
}