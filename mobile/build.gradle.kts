plugins {
    id("com.gluonhq.gluonfx-gradle-plugin") version "1.0.26"
}

// Mobile app module (Android & iOS) using Gluon Mobile and BLE

dependencies {
    implementation(project(":common"))
    implementation(project(":service"))
    implementation("com.gluonhq:charm-down-plugin-ble:4.0.12") // Bluetooth LE support
}

gluonfx {
    target = listOf("android", "ios")
    mainClass = "net.codesapien.callerble.mobile.MainApp"
} 