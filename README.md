# CallerBLE

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](#license)
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)]()

**CallerBLE** lets you share your incoming caller ID from your mobile device to your desktop client entirely over Bluetooth Low Energy (BLE), with no cloud, no internet—everything stays local and private.

---

## Table of Contents

1. [Introduction](#introduction)
2. [Use Cases](#use-cases)
3. [Features](#features)
4. [Architecture](#architecture)
5. [Modules & Project Structure](#modules--project-structure)
6. [Technology Stack](#technology-stack)
7. [Getting Started](#getting-started)
   - [Prerequisites](#prerequisites)
   - [Clone & Build](#clone--build)
   - [Desktop Client](#desktop-client)
   - [Mobile App](#mobile-app)
8. [Manual Testing Guide](#manual-testing-guide)
9. [Developer Guide](#developer-guide)
10. [Packaging & Deployment](#packaging--deployment)
11. [FAQ](#faq)
12. [Contributing](#contributing)
13. [License](#license)

---

## Introduction

CallerBLE was born from the need to see who's calling without picking up your phone or routing sensitive data through third-party servers. By leveraging BLE advertising on the mobile side and BLE scanning on the desktop side, CallerBLE delivers real-time caller information (name, number, timestamp) directly between devices.

Key motivations:

- Eliminate cloud dependencies and privacy concerns
- Provide a responsive, offline-capable solution
- Support cross-platform clients with a single codebase

## Use Cases

- **Remote Work**: Get caller alerts on your workstation while sharing your screen or in a meeting.
- **Accessibility**: Assist users with limited mobility who can't reach their phone easily.
- **Kiosk/Point-of-Sale**: Display incoming calls on public or shared terminals.
- **Development/Test**: Prototype BLE data exchange between devices.

## Features

- **BLE Broadcasting**: Mobile app broadcasts `CallerInfo` payloads via BLE.
- **BLE Listening**: Desktop app scans for BLE advertisement and decodes `CallerInfo`.
- **Local-Only**: No network connectivity or cloud services needed.
- **Cross-Platform UI**: JavaFX on desktop and Gluon Mobile on Android/iOS.
- **Modular Design**: Shared common DTOs, pluggable BLE service implementations, clean separation of concerns.

## Architecture

```text
[Mobile App] --(BLE Advertise: CallerInfo)---> [Desktop App]
    |                                           |
    |-- detects incoming call                    |-- scans BLE advertisements
    |-- serializes CallerInfo                    |-- deserializes CallerInfo
    |-- startBroadcasting(...)                   |-- onInfoReceived -> UI update
```

1. **Mobile**: Detects real incoming calls (stubbed for testing) and constructs `CallerInfo(name, number, timestamp)`.
2. **Protocol**: `CallerInfoProtocol` serializes to/from binary payload.
3. **Service Layer**: `BleBroadcaster` and `BleListener` interfaces abstract BLE operations.
4. **Implementations**: Default implementations in `service.impl` use Gluon Attach BLE.
5. **Desktop**: Listens via `BleServiceFactory.getListener()`, updates JavaFX UI.

## Modules & Project Structure

```
callerble/                 # root
├─ common/                 # DTOs & serialization
│   └─ CallerInfo.java      # payload model
│   └─ BleMessageProtocol.java
│   └─ CallerInfoProtocol.java
├─ service/                # BLE abstractions & implementations
│   └─ BleBroadcaster.java
│   └─ BleListener.java
│   └─ BleServiceFactory.java
│   └─ impl/                # DefaultBleBroadcaster, DefaultBleListener
│   └─ META-INF/services/   # ServiceLoader registrations
├─ mobile/                 # Gluon Mobile (Android + iOS)
│   └─ MainApp.java         # JavaFX entry, broadcast button
│   └─ build.gradle.kts     # GluonFX plugin + BLE dependency
├─ desktop/                # JavaFX Desktop client
│   └─ MainApp.java         # JavaFX entry, BLE listener updates label
│   └─ build.gradle.kts     # GluonFX plugin + BLE dependency
├─ settings.gradle.kts     # include modules, plugin management
├─ build.gradle.kts        # toolchain, preview features, common settings
└─ README.md               # this file
```

## Technology Stack

- **JDK 25** with [Project Loom virtual threads]
- **Gradle Kotlin DSL** for builds (`build.gradle.kts`, `settings.gradle.kts`)
- **GluonFX Gradle Plugin** v1.0.26 for packaging mobile and desktop
- **Gluon Attach BLE** v4.0.7 for cross-platform BLE operations
- **JavaFX** for UIs on both desktop and mobile
- **Docker** for containerizing the desktop client

## Getting Started

### Prerequisites

- **Java 25** installed and `JAVA_HOME` set
- **Bluetooth** hardware enabled on both devices
- **Android SDK** and/or **Xcode** (for mobile builds)
- **Windows 10**, **macOS**, or **Linux** desktop for desktop client
- **GIT** to clone repository

### Clone & Build

```bash
# Clone project
git clone https://github.com/your-org/callerble.git callerble
cd callerble

# Build all modules
./gradlew clean build --warning-mode all
```

> **Tip**: Use `./gradlew --profile` to generate build performance reports.

### Desktop Client

```bash
# Run via Gradle
./gradlew :desktop:run
```

- The UI window appears titled "CallerBLE Desktop".
- It automatically begins scanning for BLE advertisements.

### Mobile App

```bash
# Android
./gradlew :mobile:build :mobile:gluonfx:package -Ptarget=android
./gradlew :mobile:gluonfx:install -Ptarget=android
./gradlew :mobile:gluonfx:nativeRun -Ptarget=android

# iOS (Mac only)
./gradlew :mobile:build :mobile:gluonfx:package -Ptarget=ios
./gradlew :mobile:gluonfx:install -Ptarget=ios
./gradlew :mobile:gluonfx:nativeRun -Ptarget=ios
```

- On launch, tap **Broadcast Caller Info** to send a test `CallerInfo` payload.
- Observe desktop update.

## Manual Testing Guide

1. **Bluetooth Pairing**: Pair mobile and desktop in OS settings.
2. **Desktop**: Launch `:desktop:run`, confirm "Waiting for caller..." label.
3. **Mobile**: Launch app, ensure BLE advertising permission prompts are accepted.
4. **Broadcast**: Tap **Broadcast Caller Info**; mobile logs serialization, desktop logs reception.
5. **Validation**: Confirm desktop label shows `Caller: Test Caller (123-456-7890)`.
6. **Iteration**: Change test data in `mobile/MainApp.java` and rebuild to test variety.

## Developer Guide

### Packages & Namespaces

- **GroupId**: `net.codesapien.callerble`
- **Module Packages**: `common`, `service`, `mobile`, `desktop`

### Extending Functionality

1. **Incoming Call Detection**: Replace stub in `mobile/MainApp` with Android Telephony API or iOS CallKit integration.
2. **Protocol Changes**: Modify `CallerInfoProtocol` for JSON or protobuf payloads.
3. **Custom BLE Service**: Implement `BleBroadcaster`/`BleListener` in `service.impl`, register in `META-INF/services/`.
4. **Virtual Threads**: Use `Thread.startVirtualThread()` in `service` implementations for non-blocking BLE I/O.

### Testing & Mocks

- **Unit Tests**: Write JUnit tests in each module under `src/test/java`.
- **Mock BLE**: Create test `BleBroadcaster` implementations returning canned `CallerInfo` for fast CI feedback.
- **Dependency Injection**: Use `BleServiceFactory` to swap real vs. mock services.

## Packaging & Deployment

### Docker (Desktop)

1. Build JAR:

   ```bash
   ./gradlew :desktop:jar
   ```

2. Dockerfile in `desktop/`:

   ```dockerfile
   FROM eclipse-temurin:25-jre
   COPY build/libs/desktop.jar /app/callerble.jar
   ENTRYPOINT ["java", "--enable-preview", "-jar", "/app/callerble.jar"]
   ```

3. Build & Run:

   ```bash
   docker build -t callerble-desktop ./desktop
   docker run --rm callerble-desktop
   ```

### Native Images (Mobile)

- Follow [GluonFX plugin docs](https://github.com/gluonhq/gluonfx-gradle-plugin) for advanced native packaging.

## FAQ

**Q: How do I change the broadcast UUID?**  
A: In `common/CallerInfoProtocol`, adapt serialization; in `DefaultBleBroadcaster`, pass your desired `UUID` to `service.startBroadcasting(...)`.

**Q: Where to integrate real incoming calls?**  
A: In `mobile/MainApp` replace the test button handler with TelephonyManager callback on Android, and CallKit on iOS.

**Q: How to debug BLE issues?**  
A: Set system property `-Dcom.gluonhq.charm.down.debug=true` or add `System.setProperty("com.gluonhq.charm.down.debug","true");` before BLE calls.

## Contributing

1. Fork & clone repo  
2. Create feature branch: `git checkout -b feature/your-feature-name`  
3. Write code & tests  
4. Commit with descriptive message  
5. Push & open Pull Request  
6. Ensure CI passes and reviewers approve  

## License

This project is licensed under the **MIT License**. See [LICENSE](LICENSE) for details.
