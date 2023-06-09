plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose")
    id("com.google.gms.google-services")
}

kotlin {
    android()
    sourceSets {
        val androidMain by getting {
            dependencies {
                val koinVersion = rootProject.extra["koinVersion"]
                val coroutineVersion = rootProject.extra["coroutineVersion"]

                implementation(project(":shared"))

                // koin
                implementation("io.insert-koin:koin-android:$koinVersion")
                implementation("io.insert-koin:koin-core:$koinVersion")
                implementation("io.insert-koin:koin-androidx-compose:$koinVersion")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
            }
        }
    }
}

android {
    namespace = "com.andremw96.qocrkmm.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.andremw96.qocrkmm.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    sourceSets["main"].manifest.srcFile("src/main/AndroidManifest.xml")

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}
