package net.chineseguide.jukuu.navigation

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RouterImpl @Inject constructor(): Router, NavigatorHolder {

    private var navigator: Navigator? = null

    private var pendingCommand: Command? = null

    override fun setNavigator(navigator: Navigator) {
        this.navigator = navigator
        pendingCommand?.let(navigator::execute)
        pendingCommand = null
    }

    override fun clearNavigator() {
        navigator = null
    }

    override fun open(destinationId: Int) {
        val command = Command.Open(destinationId)

        navigator?.execute(command)

        if (navigator == null) {
            pendingCommand = command
        }
    }

    override fun pop() {
        val command = Command.Pop

        navigator?.execute(command)

        if (navigator == null) {
            pendingCommand = command
        }
    }
}