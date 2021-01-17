package net.chineseguide.jukuu.presentation.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.chineseguide.jukuu.domain.entity.Sentence
import net.chineseguide.jukuu.domain.usecase.GetNextSentencesUseCase
import net.chineseguide.jukuu.domain.usecase.GetSentenceCollectionUseCase
import net.chineseguide.jukuu.presentation.ErrorLogger

class HomeViewModel @ViewModelInject constructor(
    private val getSentenceCollectionUseCase: GetSentenceCollectionUseCase,
    private val getNextSentencesUseCase: GetNextSentencesUseCase,
    private val errorLogger: ErrorLogger
) : ViewModel() {

    private val _state = MutableLiveData<HomeState>(HomeState.EmptyNoSearch)
    val state: LiveData<HomeState> = _state

    fun search(query: String) {
        _state.value = HomeState.Progress
        viewModelScope.launch(context = IO) {
            runCatching { getSentenceCollectionUseCase(query) }
                .onSuccess { showFirstPageLoads(it) }
                .onFailure { showQueryError(it) }
        }
    }

    private suspend fun showFirstPageLoads(sentences: List<Sentence>) {
        withContext(Main) {
            if (sentences.isEmpty()) {
                _state.value = HomeState.EmptyResultAfterSearch
            } else {
                _state.value = HomeState.Content(sentences)
            }
        }
    }

    private suspend fun showQueryError(error: Throwable) {
        withContext(Main) {
            errorLogger.log(error)
            _state.value = HomeState.Error(error)
        }
    }

    fun listScrolled(query: String, sentenceIndex: Int) {
        viewModelScope.launch(context = IO) {
            runCatching { getNextSentencesUseCase(query, sentenceIndex) }
                .onSuccess { showNextSentencesLoaded(it) }
        }
    }

    private suspend fun showNextSentencesLoaded(nextSentences: List<Sentence>) {
        withContext(Main) {
            val currentState = state.value
            if (nextSentences.isNotEmpty() && currentState is HomeState.Content) {
                _state.value = HomeState.Content(currentState.sentences + nextSentences)
            }
        }
    }
}