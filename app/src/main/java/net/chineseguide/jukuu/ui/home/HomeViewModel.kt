package net.chineseguide.jukuu.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.chineseguide.jukuu.domain.entity.SentenceCollection
import net.chineseguide.jukuu.domain.usecase.GetSentenceCollectionUseCase
import net.chineseguide.jukuu.executeCoroutine
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getSentenceCollectionUseCase: GetSentenceCollectionUseCase
) : ViewModel() {

    private val _state = MutableLiveData<HomeState>()
    val state: LiveData<HomeState> = _state

    fun search(query: String) {
        _state.value = HomeState.Progress
        executeCoroutine { getSentenceCollectionUseCase(query) }
            .observeOnMain()
            .onSuccess(::showSuccess)
            .onError {
                _state.value = HomeState.Error(it)
            }
    }

    private fun showSuccess(sentenceCollection: SentenceCollection) {
        if (sentenceCollection.sentences.isEmpty()) {
            _state.value = HomeState.EmptyResult
        } else {
            _state.value = HomeState.Success(sentenceCollection)
        }
    }
}