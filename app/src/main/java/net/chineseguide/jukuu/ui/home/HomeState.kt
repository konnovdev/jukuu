package net.chineseguide.jukuu.ui.home

import net.chineseguide.jukuu.domain.entity.SentenceCollection

sealed class HomeState {

    object EmptyNoSearch : HomeState()

    object Progress : HomeState()

    class Success(val sentenceCollection: SentenceCollection) : HomeState()

    object EmptyResultAfterSearch : HomeState()

    class Error(val exception: Throwable) : HomeState()
}
