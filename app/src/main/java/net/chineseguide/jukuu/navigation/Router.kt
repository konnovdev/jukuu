package net.chineseguide.jukuu.navigation

interface Router {

    fun open(destinationId: Int)

    fun pop()
}