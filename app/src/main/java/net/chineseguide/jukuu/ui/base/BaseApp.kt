package net.chineseguide.jukuu.ui.base

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import net.chineseguide.jukuu.ui.util.config.LocaleManager
import net.chineseguide.jukuu.ui.util.config.ThemeManager

@HiltAndroidApp
abstract class BaseApp : Application() {

    override fun attachBaseContext(base: Context) {
        val contextWithCorrectTheme =
            ThemeManager.createThemeConfiguration(base)
        val contextWithUserSelectedLocale =
            LocaleManager.createLanguageConfiguration(
                contextWithCorrectTheme
            )
        super.attachBaseContext(contextWithUserSelectedLocale)
    }
}