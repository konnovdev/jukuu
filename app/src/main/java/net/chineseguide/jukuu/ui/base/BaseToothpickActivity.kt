package net.chineseguide.jukuu.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import net.chineseguide.jukuu.di.Scopes
import toothpick.Toothpick

@Deprecated("No need for hilt")
abstract class BaseToothpickActivity(@LayoutRes layout: Int) : AppCompatActivity(layout) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpToothpick()
    }

    private fun setUpToothpick() {
        val activityScope = Toothpick.openScopes(Scopes.APP, Scopes.ACTIVITY)
        Toothpick.inject(this, activityScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toothpick.closeScope(Scopes.ACTIVITY)
    }
}