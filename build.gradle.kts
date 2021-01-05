// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Classpaths.gradleAndroidPlugin)
        classpath(Classpaths.navigationPlugin)
        classpath(Classpaths.googleServices)
        classpath(Classpaths.crashlyticsGradle)
        classpath(Classpaths.gradleDaggerHilt)
        classpath(kotlin("gradle-plugin", version = Versions.kotlin))
    }
}

plugins {
    base
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

repositories {
    mavenCentral()
}

tasks.create<Delete>("cleanRP") {
    group = "rp"
    delete = setOf(
        "rp-out", "rp-zip"
    )
}
