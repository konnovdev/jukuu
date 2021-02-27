package net.chineseguide.jukuu.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import net.chineseguide.jukuu.R
import javax.inject.Inject

interface Navigator {

    fun execute(command: Command)
}

class JetpackNavigator @Inject constructor(
    private val activity: AppCompatActivity
) : Navigator {

    override fun execute(command: Command) {
        when (command) {
            is Command.Open -> openDestination(command.destinationId)
            is Command.Pop -> popDestination()
        }
    }

    private fun openDestination(destinationId: Int) {
        val navController = getNavController()

        if (navController.currentDestination?.id != destinationId) {
            runCatching { navController.getBackStackEntry(destinationId) }
                .onSuccess { navController.popBackStack(destinationId, false) }
                .onFailure { navController.navigate(destinationId) }
        }
    }

    private fun popDestination() {
        val navController = getNavController()

        val poppedToPreviousFragment = navController.popBackStack()

        if (!poppedToPreviousFragment) {
            activity.finish()
        }
    }

    private fun getNavController(): NavController =
        Navigation.findNavController(activity, R.id.nav_host_fragment)
}