plugins {
    `java-library`
}

// API/service module for defining and implementing BLE and caller ID service interfaces

dependencies {
    implementation(project(":common"))
    // TODO: Add service module dependencies (e.g., BLE server libraries) here
} 