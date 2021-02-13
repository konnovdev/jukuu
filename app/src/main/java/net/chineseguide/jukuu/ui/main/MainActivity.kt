package net.chineseguide.jukuu.ui.main

import dagger.hilt.android.AndroidEntryPoint
import net.chineseguide.jukuu.R
import net.chineseguide.jukuu.navigation.Navigator
import net.chineseguide.jukuu.navigation.NavigatorHolder
import net.chineseguide.jukuu.ui.base.BaseActivity
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity(R.layout.activity_main) {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var navigator: Navigator

    override fun onResumeFragments() {
        navigatorHolder.setNavigator(navigator)
        super.onResumeFragments()
    }

    override fun onPause() {
        navigatorHolder.clearNavigator()
        super.onPause()
    }
}