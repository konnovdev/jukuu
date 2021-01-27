package net.chineseguide.jukuu.ui

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
abstract class BaseApp : Application() {

    override fun attachBaseContext(base: Context) {
        val contextWithCorrectTheme = ThemeManager.createThemeConfiguration(base)
        val contextWithUserSelectedLocale = LocaleManager.createLanguageConfiguration(contextWithCorrectTheme)
        super.attachBaseContext(contextWithUserSelectedLocale)
    }
}