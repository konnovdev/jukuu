package net.chineseguide.jukuu.ui.home

import net.chineseguide.jukuu.domain.entity.SentenceCollection

sealed class HomeState {

    object Progress : HomeState()

    class Success(val sentenceCollection: SentenceCollection) : HomeState()

    object EmptyResult : HomeState()

    object ServiceUnavailable : HomeState()

    object ParcingException : HomeState()
}
