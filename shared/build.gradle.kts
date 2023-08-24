import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("kotlin-kapt")
    id("kotlinx-serialization")
    id("kotlin-parcelize")
    id("com.codingfeline.buildkonfig")
}

buildkonfig {
    packageName = "com.andremw96.qocrkmm"

    defaultConfigs {
        buildConfigField(STRING, "BASE_API_URL", "https://api.openai.com/v1/")
        buildConfigField(STRING, "CHAT_GPT_API_KEY", "/*FILL IN WITH YOUR CHAT GPT API KEY*/")
    }
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = "1.0.0"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
        pod("GoogleMLKit/TextRecognition") {
            moduleName = "MLKitTextRecognition"
        }
        pod("GoogleMLKit/Vision") {
            moduleName = "MLKitVision"
        }
    }
    
    sourceSets {
        val koinVersion = rootProject.extra["koinVersion"]
        val ktorVersion = rootProject.extra["ktorVersion"]
        val coroutineVersion = rootProject.extra["coroutineVersion"]

        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                // Koin Core features
                api("io.insert-koin:koin-core:$koinVersion")
                api("io.insert-koin:koin-test:$koinVersion")

                // Ktor
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                val androidComposeVersion = rootProject.extra["androidComposeVersion"]
                val activityComposeVersion = rootProject.extra["activityComposeVersion"]
                val cameraxVersion = rootProject.extra["cameraxVersion"]

                api("androidx.compose.ui:ui:$androidComposeVersion")
                api("androidx.compose.ui:ui-tooling:$androidComposeVersion")
                api("androidx.compose.ui:ui-tooling-preview:$androidComposeVersion")
                api("androidx.compose.foundation:foundation:$androidComposeVersion")
                api("androidx.compose.material:material:$androidComposeVersion")
                api("androidx.activity:activity-compose:$activityComposeVersion")
                implementation("com.google.accompanist:accompanist-permissions:0.29.2-rc")

                implementation("io.ktor:ktor-client-android:$ktorVersion")
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")

                // camerax
                implementation("androidx.camera:camera-camera2:$cameraxVersion")
                implementation("androidx.camera:camera-lifecycle:$cameraxVersion")
                implementation("androidx.camera:camera-view:$cameraxVersion")

                // mlkit
                implementation("com.google.android.gms:play-services-mlkit-text-recognition:19.0.0")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }
    }
}

android {
    namespace = "com.andremw96.qocrkmm"
    compileSdk = 33

    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
    
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}
