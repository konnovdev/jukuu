package net.chineseguide.jukuu.presentation.home

import net.chineseguide.jukuu.domain.entity.Sentence

sealed class HomeState {

    object EmptyNoSearch : HomeState()

    object Progress : HomeState()

    class Content(val sentences: List<Sentence>) : HomeState()

    object EmptyResultAfterSearch : HomeState()

    object Error: HomeState()
}
