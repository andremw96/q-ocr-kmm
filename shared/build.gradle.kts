plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
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
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
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

                api("androidx.compose.ui:ui:$androidComposeVersion")
                api("androidx.compose.ui:ui-tooling:$androidComposeVersion")
                api("androidx.compose.ui:ui-tooling-preview:$androidComposeVersion")
                api("androidx.compose.foundation:foundation:$androidComposeVersion")
                api("androidx.compose.material:material:$androidComposeVersion")
                api("androidx.activity:activity-compose:$activityComposeVersion")
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
