buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        google()
    }
    dependencies {
        classpath("com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:0.13.3")
        classpath("com.google.gms:google-services:4.3.15")
    }

    extra.apply {
        set("androidComposeVersion", "1.4.1")
        set("activityComposeVersion", "1.7.0")

        // koin
        set("koinVersion", "3.2.2")

        // ktor
        set("ktorVersion", "2.1.2")
        set("coroutineVersion", "1.6.4")

        // gson
        set("gsonVersion", "2.10")

        // cameraX
        set("cameraxVersion", "1.2.2")

        // compose navigation version
        set("androidComposeNavVersion", "2.5.2")
    }
}

plugins {
    //trick: for the same plugin versions in all sub-modules
    kotlin("multiplatform").version("1.8.20").apply(false)
    id("com.android.application").version("7.4.2").apply(false)
    id("com.android.library").version("7.4.2").apply(false)
    kotlin("android").version("1.8.20").apply(false)
    id("org.jetbrains.compose").version("1.4.0")apply(false)
    kotlin("plugin.serialization").version("1.7.10").apply(false)
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
