object Versions {
    const val gradleAndroidPlugin = "3.6.0"
    const val kotlin = "1.3.61"
    const val toothpick = "3.1.0"

    const val jsoup = "1.13.1"

    const val appCompat = "1.1.0"
    const val material = "1.1.0"
    const val constraintLayout = "1.1.3"
    const val coordinatorLayout = "1.1.0"
    const val fragment = "1.2.2"
    const val lifecycle = "2.2.0"
    const val navigation = "2.3.0-alpha04"
}

object Classpaths {
    const val gradleAndroidPlugin = "com.android.tools.build:gradle:${Versions.gradleAndroidPlugin}"
    const val navigationPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"

}

object Libraries {
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

    const val toothpickCore = "com.github.stephanenicolas.toothpick:toothpick-runtime:${Versions.toothpick}"
    const val toothpickCompiler = "com.github.stephanenicolas.toothpick:toothpick-compiler:${Versions.toothpick}"

    const val jsoup = "org.jsoup:jsoup:${Versions.jsoup}"

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val coordinatorLayout = "androidx.coordinatorlayout:coordinatorlayout:${Versions.coordinatorLayout}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata:${Versions.lifecycle}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val navigationFragment = "androidx.navigation:navigation-fragment:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui:${Versions.navigation}"
}