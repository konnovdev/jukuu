package net.chineseguide.jukuu.ui.base

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import net.chineseguide.jukuu.di.Scopes
import toothpick.Toothpick

@Deprecated("No need for hilt")
abstract class BaseToothpickDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpToothpick()
    }

    private fun setUpToothpick() {
        val activityScope = Toothpick.openScopes(Scopes.FRAGMENT, Scopes.NESTED_FRAGMENT)
        Toothpick.inject(this, activityScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toothpick.closeScope(Scopes.NESTED_FRAGMENT)
    }
}