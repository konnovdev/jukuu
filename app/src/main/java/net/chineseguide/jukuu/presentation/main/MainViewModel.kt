package net.chineseguide.jukuu.presentation.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import net.chineseguide.jukuu.navigation.Router
import net.chineseguide.jukuu.navigation.homeScreen
import net.chineseguide.jukuu.navigation.settingsScreen

class MainViewModel @ViewModelInject constructor(
    private val router: Router
) : ViewModel() {

    fun back() {
        router.pop()
    }

    fun openHomeScreen() {
        router.open(homeScreen)
    }

    fun openSettingsScreen() {
        router.open(settingsScreen)
    }
}