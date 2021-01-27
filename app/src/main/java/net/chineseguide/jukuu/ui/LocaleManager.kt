package net.chineseguide.jukuu.ui

import android.content.Context
import android.content.res.Configuration
import net.chineseguide.jukuu.ui.util.getLocale
import java.util.*

object LocaleManager {

    private const val FILE_NAME = "app_settings"
    private const val KEY_LANGUAGE = "key_language"

    fun createLanguageConfiguration(context: Context): Context {
        val config = Configuration(context.resources.configuration)
        val localeSet = getLocale(context)
        config.setLocale(Locale(localeSet.last(), localeSet.first()))

        return context.createConfigurationContext(config)
    }

    private fun getLocale(context: Context): Set<String> =
        context
            .getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
            .getStringSet(
                KEY_LANGUAGE,
                setOf(context.resources.getLocale().language, context.resources.getLocale().country)
            ) ?: setOf(context.resources.getLocale().language, context.resources.getLocale().country)

    fun saveLocale(context: Context, languageCode: String, country: String = "") {
        context
            .getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
            .edit()
            .putStringSet(KEY_LANGUAGE, setOf(languageCode, country))
            .apply()
    }
}