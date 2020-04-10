package net.chineseguide.jukuu.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import net.chineseguide.jukuu.di.Scopes
import net.chineseguide.jukuu.di.module.DetailsModule
import net.chineseguide.jukuu.ui.detail.DetailsFragment
import toothpick.Toothpick
import toothpick.config.Module
import java.lang.IllegalArgumentException

class CommonLifecycleCallback : FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        val scope = Toothpick.openScopes(Scopes.ACTIVITY, Scopes.FRAGMENT)

        createModuleByFragment(f)?.let { scope.installModules(it) }

        Toothpick.inject(f, scope)
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        super.onFragmentDestroyed(fm, f)
        Toothpick.closeScope(Scopes.FRAGMENT)
    }

    private fun createModuleByFragment(fragment: Fragment): Module? {
        val args = fragment.arguments

        return when (fragment) {
            is DetailsFragment -> DetailsModule(args.require())
            else -> null
        }
    }

    private fun Bundle?.require(): Bundle =
        this ?: throw IllegalArgumentException("\"arguments\" are null")
}