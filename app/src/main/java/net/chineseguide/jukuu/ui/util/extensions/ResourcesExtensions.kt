package net.chineseguide.jukuu.ui.util.extensions

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