package net.chineseguide.jukuu.ui.base

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import net.chineseguide.jukuu.ui.util.config.LocaleManager
import net.chineseguide.jukuu.ui.util.config.ThemeManager

@AndroidEntryPoint
abstract class BaseActivity(@LayoutRes layoutId: Int) : AppCompatActivity(layoutId) {

    override fun attachBaseContext(base: Context) {
        val contextWithUserSelectedLocale =
            LocaleManager.createLanguageConfiguration(
                base
            )
        val contextWithCorrectTheme =
            ThemeManager.createThemeConfiguration(
                contextWithUserSelectedLocale
            )
        super.attachBaseContext(contextWithCorrectTheme)
    }
}