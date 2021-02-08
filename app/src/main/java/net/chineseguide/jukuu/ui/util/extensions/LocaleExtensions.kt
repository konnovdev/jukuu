package net.chineseguide.jukuu.ui.util.extensions

import net.chineseguide.jukuu.domain.entity.AppLocale
import java.util.*

fun Locale.toAppLocale(): AppLocale =
    when (this) {
        Locale.ENGLISH -> AppLocale.ENGLISH
        Locale("ru", "") -> AppLocale.RUSSIAN
        Locale.TAIWAN -> AppLocale.TRADITIONAL_CHINESE
        Locale.CHINESE -> AppLocale.CHINESE
        else -> AppLocale.default()
    }