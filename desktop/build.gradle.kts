import org.gradle.kotlin.dsl.closureOf

plugins {
    id("com.gluonhq.gluonfx-gradle-plugin") version "1.0.26"
    application
}

// Desktop client module (Win/Mac/Linux) using JavaFX and BLE

application {
    mainClass.set("net.codesapien.callerble.desktop.MainApp")
}

dependencies {
    implementation(project(":common"))
    implementation(project(":service"))
    // BLE support is handled by the GluonFX plugin DSL
}

gluonfx {
    // Only desktop build
    target = "desktop"
    // Attach BLE service via the GluonFX plugin DSL
    attachConfig(closureOf<Any> {
        services("ble")
    })
} 