This is a demonstration of how Hotwire native can be deployed via Kotlin Multiplatform project targeting Android, iOS, maybe also Desktop.
The core functionality to be demonstrated is a shared database for local storage

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
    - `commonMain` is for code that’s common for all targets.
    - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
      For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
      `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

To run:
 - **Android** - Just run from Android Studio on a virtual or physical device
 - **ios** - on a mac, you can also run `iosMain`. You will need XCode (or its simulator) running.
 - **desktop** - from a terminal: `./gradlew run`
