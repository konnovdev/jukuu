package net.chineseguide.jukuu.navigation

sealed class Command {

    data class Open(val destinationId: Int) : Command()

    object Pop : Command()
}