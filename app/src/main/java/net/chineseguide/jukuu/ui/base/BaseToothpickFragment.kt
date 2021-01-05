package net.chineseguide.jukuu.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import net.chineseguide.jukuu.di.Scopes
import toothpick.Toothpick

@Deprecated("No need for hilt")
abstract class BaseToothpickFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpToothpick()
    }

    private fun setUpToothpick() {
        val activityScope = Toothpick.openScopes(Scopes.ACTIVITY, Scopes.FRAGMENT)
        Toothpick.inject(this, activityScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toothpick.closeScope(Scopes.FRAGMENT)
    }
}