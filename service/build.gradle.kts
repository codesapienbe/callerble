plugins {
    `java-library`
}

// API/service module for defining and implementing BLE and caller ID service interfaces

dependencies {
    implementation(project(":common"))
    // BLE Attach library for implementing BLE services
    implementation("com.gluonhq.attach:ble:4.0.7")
} 