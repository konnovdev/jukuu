object Versions {
    const val gradleAndroidPlugin = "3.6.0"
    const val kotlin = "1.3.61"
    const val toothpick = "3.1.0"
    const val daggerHilt = "2.28-alpha"
    const val hiltLifecycleViewModel = "1.0.0-alpha01"

    const val jsoup = "1.13.1"

    const val appCompat = "1.1.0"
    const val material = "1.1.0"
    const val constraintLayout = "1.1.3"
    const val coordinatorLayout = "1.1.0"
    const val fragment = "1.2.2"
    const val lifecycle = "2.2.0"
    const val navigation = "2.3.0-alpha04"

    const val googleServices = "4.3.4"
    const val crashlyticsGradle = "2.4.1"
    const val firebase = "26.2.0"

    const val junit = "4.13.1"
}

object Classpaths {
    const val gradleAndroidPlugin = "com.android.tools.build:gradle:${Versions.gradleAndroidPlugin}"
    const val navigationPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    const val googleServices = "com.google.gms:google-services:${Versions.googleServices}"
    const val crashlyticsGradle = "com.google.firebase:firebase-crashlytics-gradle:${Versions.crashlyticsGradle}"
    const val gradleDaggerHilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerHilt}"
}

object Libraries {
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

    const val toothpickCore = "com.github.stephanenicolas.toothpick:toothpick-runtime:${Versions.toothpick}"
    const val toothpickCompiler = "com.github.stephanenicolas.toothpick:toothpick-compiler:${Versions.toothpick}"
    const val daggerHiltCore = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
    const val daggerHiltAnnotationProcessor = "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}"
    const val hiltLifecycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltLifecycleViewModel}"
    const val hiltAndroidxCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltLifecycleViewModel}"

    const val jsoup = "org.jsoup:jsoup:${Versions.jsoup}"

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val coordinatorLayout = "androidx.coordinatorlayout:coordinatorlayout:${Versions.coordinatorLayout}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata:${Versions.lifecycle}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val navigationFragment = "androidx.navigation:navigation-fragment:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui:${Versions.navigation}"

    const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebase}"
    // When using the BoM, don't specify versions in Firebase dependencies
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
    const val crashlytics = "com.google.firebase:firebase-crashlytics"
}

object TestLibraries {
    const val junit = "junit:junit:${Versions.junit}"
}