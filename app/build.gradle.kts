plugins {
    id("com.android.application")
    id("androidx.navigation.safeargs")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(Application.maxApi)
    defaultConfig {
        applicationId = Application.id
        minSdkVersion(Application.minApi)
        targetSdkVersion(Application.maxApi)
        versionCode = Application.versionCode
        versionName = Application.versionName
    }
    viewBinding.isEnabled = true
    lintOptions {
        setLintConfig(file("lint.xml"))
    }
}

dependencies {
    implementation(Libraries.kotlinStdlib)

    implementation(Libraries.toothpickCore)
    kapt(Libraries.toothpickCompiler)

    implementation(Libraries.jsoup)

    implementation(Libraries.appCompat)
    implementation(Libraries.material)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.coordinatorLayout)
    implementation(Libraries.liveData)
    implementation(Libraries.fragmentKtx)
    implementation(Libraries.navigationFragment)
    implementation(Libraries.navigationUi)

    testImplementation(TestLibraries.junit)
}