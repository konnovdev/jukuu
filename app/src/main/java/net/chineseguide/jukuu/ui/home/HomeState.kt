package net.chineseguide.jukuu.ui.home

import net.chineseguide.jukuu.domain.entity.Result

sealed class HomeState {

    object Progress : HomeState()

    class Success(val resultList: List<Result>) : HomeState()

    object EmptyResult : HomeState()

    object ServiceUnavailable : HomeState()

    object ParcingException : HomeState()
}
