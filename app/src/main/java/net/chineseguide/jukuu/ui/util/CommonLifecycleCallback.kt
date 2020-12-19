package net.chineseguide.jukuu.ui.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import net.chineseguide.jukuu.di.Scopes
import toothpick.Toothpick

class CommonLifecycleCallback : FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        val scope = Toothpick.openScopes(Scopes.ACTIVITY, Scopes.FRAGMENT)

        Toothpick.inject(f, scope)
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        super.onFragmentDestroyed(fm, f)
        Toothpick.closeScope(Scopes.FRAGMENT)
    }
}