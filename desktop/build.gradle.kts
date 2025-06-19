plugins {
    id("org.gluonhq.gluonfx-gradle-plugin") version "1.0.11"
    application
}

// Desktop client module (Win/Mac/Linux) using JavaFX and BLE

application {
    mainClass.set("net.codesapien.callerble.desktop.MainApp")
}

dependencies {
    implementation(project(":common"))
    implementation("com.gluonhq:charm-down-plugin-ble:4.0.12") // Bluetooth LE support
}

gluonfx {
    target = listOf("desktop")
} 