package net.chineseguide.jukuu.ui

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity(@LayoutRes layoutId: Int) : AppCompatActivity(layoutId) {

    override fun attachBaseContext(base: Context) {
        val contextWithUserSelectedLocale = LocaleManager.createLanguageConfiguration(base)
        val contextWithCorrectTheme = ThemeManager.createThemeConfiguration(contextWithUserSelectedLocale)
        super.attachBaseContext(contextWithCorrectTheme)
    }
}