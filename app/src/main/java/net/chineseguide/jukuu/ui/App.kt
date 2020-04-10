package net.chineseguide.jukuu.ui

import android.app.Application
import androidx.viewbinding.BuildConfig
import net.chineseguide.jukuu.di.Scopes
import net.chineseguide.jukuu.di.module.DataModule
import toothpick.Toothpick
import toothpick.configuration.Configuration

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setUpToothpick()
    }

    private fun setUpToothpick() {
        Toothpick.setConfiguration(
            if (BuildConfig.DEBUG) {
                Configuration.forDevelopment().preventMultipleRootScopes()
            } else {
                Configuration.forProduction()
            }
        )
        val appScope = Toothpick.openScope(Scopes.APP)
        appScope.installModules(DataModule())
    }
}