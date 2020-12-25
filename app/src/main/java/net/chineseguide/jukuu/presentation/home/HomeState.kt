package net.chineseguide.jukuu.presentation.home

import net.chineseguide.jukuu.domain.entity.SentenceCollection

sealed class HomeState {

    object EmptyNoSearch : HomeState()

    object Progress : HomeState()

    class Content(val sentenceCollection: SentenceCollection) : HomeState()

    object EmptyResultAfterSearch : HomeState()

    class Error(val exception: Throwable) : HomeState()
}
