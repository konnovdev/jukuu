package net.chineseguide.jukuu.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.chineseguide.jukuu.domain.entity.Sentence
import net.chineseguide.jukuu.domain.entity.SentenceCollection
import net.chineseguide.jukuu.domain.usecase.GetNextSentencesUseCase
import net.chineseguide.jukuu.domain.usecase.GetSentenceCollectionUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getSentenceCollectionUseCase: GetSentenceCollectionUseCase,
    private val getNextSentencesUseCase: GetNextSentencesUseCase
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

    private suspend fun showFirstPageLoads(sentenceCollection: SentenceCollection) {
        withContext(Main) {
            if (sentenceCollection.sentences.isEmpty()) {
                _state.value = HomeState.EmptyResultAfterSearch
            } else {
                _state.value = HomeState.Content(sentenceCollection)
            }
        }
    }

    private suspend fun showQueryError(error: Throwable) {
        withContext(Main) {
            FirebaseCrashlytics.getInstance().recordException(error)
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
            if (nextSentences.isNotEmpty()) {
                val contentState = (state.value as HomeState.Content)
                val currentSentenceCollection = contentState.sentenceCollection
                currentSentenceCollection.sentences.addAll(nextSentences)
                _state.value = HomeState.Content(currentSentenceCollection)
            }
        }
    }
}