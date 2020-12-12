package net.chineseguide.jukuu.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.chineseguide.jukuu.domain.entity.SentenceCollection
import net.chineseguide.jukuu.domain.usecase.GetNextSentencesUseCase
import net.chineseguide.jukuu.domain.usecase.GetSentenceCollectionUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getSentenceCollectionUseCase: GetSentenceCollectionUseCase,
    private val getNextSentencesUseCase: GetNextSentencesUseCase
) : ViewModel() {

    private companion object {
        const val JUKUU_PAGES_LIMIT = 10
        const val SENTENCES_PER_PAGE = 10
        const val FIRST_PAGE = 0
    }

    private val _state = MutableLiveData<HomeState>(HomeState.EmptyNoSearch)
    val state: LiveData<HomeState> = _state
    private var pageCounter = FIRST_PAGE
    private var loadingNextSentencesNotAllowed = false

    fun search(query: String) {
        pageCounter = FIRST_PAGE
        loadingNextSentencesNotAllowed = false
        _state.value = HomeState.Progress
        viewModelScope.launch(context = IO) {
            runCatching { getSentenceCollectionUseCase(query) }
                .onSuccess { showFirstPageLoads(it) }
                .onFailure { showQueryError(it) }
        }
    }

    private suspend fun showFirstPageLoads(sentenceCollection: SentenceCollection) {
        withContext(Main) {
            if (sentenceCollection.sentences.isEmpty()) {
                _state.value = HomeState.EmptyResultAfterSearch
            } else {
                _state.value = HomeState.FirstSentencesLoaded(sentenceCollection)
            }
        }
    }

    private suspend fun showQueryError(error: Throwable) {
        withContext(Main) {
            _state.value = HomeState.Error(error)
        }
    }

    fun listScrolled(query: String, messageIndex: Int) {
        val page = messageIndex / SENTENCES_PER_PAGE
        if (page == pageCounter || page == JUKUU_PAGES_LIMIT || loadingNextSentencesNotAllowed) {
            return
        }
        pageCounter++
        viewModelScope.launch(context = IO) {
            runCatching { getNextSentencesUseCase(query, pageCounter) }
                .onSuccess { showNextSentencesLoaded(it) }
                .onFailure { handleNextSentencesError() }
        }
    }

    private suspend fun showNextSentencesLoaded(sentenceCollection: SentenceCollection) {
        withContext(Main) {
            if (sentenceCollection.sentences.isEmpty()) {
                _state.value = HomeState.EmptyResultAfterSearch
            } else {
                _state.value = HomeState.NextSentencesLoaded(sentenceCollection)
            }
        }
    }

    private fun handleNextSentencesError() {
        loadingNextSentencesNotAllowed = true
    }
}