plugins {
    id("com.gluonhq.gluonfx-gradle-plugin") version "1.0.26"
    application
}

// Mobile app module (Android & iOS) using Gluon Mobile and BLE

dependencies {
    implementation(project(":common"))
    implementation(project(":service"))
    implementation("com.gluonhq:charm-down-plugin-ble:4.0.12") // Bluetooth LE support
}

gluonfx {
    // For manual testing on Windows, target Android only. Use CLI flag or override in Mac for iOS.
    target = "android"
}

application {
    // Define the mobile app entry point for JVM run or GluonFX packaging
    mainClass.set("net.codesapien.callerble.mobile.MainApp")
} 