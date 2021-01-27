package net.chineseguide.jukuu.ui.util

import android.content.res.Resources
import android.os.Build
import net.chineseguide.jukuu.domain.entity.AppLocale
import java.util.*

fun Resources.getLocale(): Locale =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        this.configuration.locales[0]
    } else {
        this.configuration.locale
    }

fun Locale.toAppLocale(): AppLocale =
    when (this) {
        Locale.ENGLISH -> AppLocale.ENGLISH
        Locale("ru", "") -> AppLocale.RUSSIAN
        Locale.TAIWAN -> AppLocale.TRADITIONAL_CHINESE
        Locale.CHINESE -> AppLocale.CHINESE
        else -> AppLocale.default()
    }