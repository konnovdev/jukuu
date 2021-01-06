plugins {
    id("com.android.application")
    id("androidx.navigation.safeargs")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("dagger.hilt.android.plugin")
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
        isCheckDependencies = true
        isCheckGeneratedSources = true
        isWarningsAsErrors = true
        isIgnoreTestSources = true
        isCheckAllWarnings = true
        isShowAll = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Libraries.kotlinStdlib)

    implementation(Libraries.daggerHiltCore)
    implementation(Libraries.hiltLifecycleViewModel)
    kapt(Libraries.daggerHiltAnnotationProcessor)
    kapt(Libraries.hiltAndroidxCompiler)

    implementation(Libraries.jsoup)

    implementation(Libraries.appCompat)
    implementation(Libraries.material)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.coordinatorLayout)
    implementation(Libraries.liveData)
    implementation(Libraries.fragmentKtx)
    implementation(Libraries.navigationFragment)
    implementation(Libraries.navigationUi)

    implementation(platform(Libraries.firebaseBom))
    implementation(Libraries.firebaseAnalytics)
    implementation(Libraries.crashlytics)

    testImplementation(TestLibraries.junit)
}

kapt {
    correctErrorTypes = true
}