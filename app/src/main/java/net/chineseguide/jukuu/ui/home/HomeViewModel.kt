package net.chineseguide.jukuu.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.chineseguide.jukuu.domain.usecase.GetSentenceCollectionUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getSentenceCollectionUseCase: GetSentenceCollectionUseCase) : ViewModel() {

    private val _state = MutableLiveData<HomeState>()
    val state: LiveData<HomeState> = _state

    fun search(query: String) {
        _state.value = HomeState.Progress
        CoroutineScope(IO).launch {
            val result = getSentenceCollectionUseCase(query)
            withContext(Main) {
                _state.value = HomeState.Success(result)
            }
        }
    }
}