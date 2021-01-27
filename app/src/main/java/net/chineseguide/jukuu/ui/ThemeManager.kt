package net.chineseguide.jukuu.ui

import android.content.Context
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES

object ThemeManager {

    private const val FILE_NAME = "app_settings"
    private const val KEY_NIGHT_THEME_ON = "night_theme_on"

    private const val NIGHT_MODE_ON = UI_MODE_NIGHT_YES and 0x6e
    private const val NIGHT_MODE_OFF = UI_MODE_NIGHT_NO and 0x13

    fun createThemeConfiguration(context: Context): Context {
        val config = Configuration(context.resources.configuration)
        val nightThemeOn = getNightThemeOn(context)

        config.uiMode = if (nightThemeOn) {
            NIGHT_MODE_ON
        } else {
            NIGHT_MODE_OFF
        }
        return context.createConfigurationContext(config)
    }

    private fun getNightThemeOn(context: Context): Boolean =
        context
            .getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
            .getBoolean(KEY_NIGHT_THEME_ON, isNightThemeOnByTheSystem(context))

    private fun isNightThemeOnByTheSystem(context: Context) =
        context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK ==
                UI_MODE_NIGHT_YES

    fun saveNightThemeOn(context: Context, nightThemeOn: Boolean) {
        context
            .getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(KEY_NIGHT_THEME_ON, nightThemeOn)
            .apply()
    }
}