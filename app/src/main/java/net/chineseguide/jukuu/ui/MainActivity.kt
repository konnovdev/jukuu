package net.chineseguide.jukuu.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.chineseguide.jukuu.R
import net.chineseguide.jukuu.di.Scopes
import toothpick.Toothpick

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpToothpick()

        supportFragmentManager.registerFragmentLifecycleCallbacks(CommonLifecycleCallback(), true)
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