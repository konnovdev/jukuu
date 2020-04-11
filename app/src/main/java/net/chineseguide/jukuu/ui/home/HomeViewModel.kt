package net.chineseguide.jukuu.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import net.chineseguide.jukuu.domain.usecase.GetResultListUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getResultListUseCase: GetResultListUseCase) : ViewModel() {

    private val _state = MutableLiveData<HomeState>()
    val state: LiveData<HomeState> = _state

    fun search(query: String) {
        _state.value = HomeState.Progress
        CoroutineScope(IO).launch {
            val result = getResultListUseCase(query)
            println("result epta $result")
        }
    }
}