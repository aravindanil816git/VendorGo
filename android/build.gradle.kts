// Top-level build file where you add configuration options common to all sub-projects/modules.

plugins {
    id("com.android.application") apply false  // ✅ Version specify na karein
    id("com.android.library") apply false
    id("org.jetbrains.kotlin.android") apply false
}

buildscript {
    dependencies {
        // ✅ Firebase Google Services Plugin ka version specify karein
        classpath("com.google.gms:google-services:4.3.15")  // Check latest version from Firebase docs
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

// ✅ Configure Build Directory Properly
val newBuildDir: Directory = rootProject.layout.buildDirectory.dir("../../build").get()
rootProject.layout.buildDirectory.value(newBuildDir)

subprojects {
    val newSubprojectBuildDir: Directory = newBuildDir.dir(project.name)
    project.layout.buildDirectory.value(newSubprojectBuildDir)
}

// ✅ Ensure Dependencies are Evaluated
subprojects {
    project.evaluationDependsOn(":app")
}

// ✅ Clean Task
tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
