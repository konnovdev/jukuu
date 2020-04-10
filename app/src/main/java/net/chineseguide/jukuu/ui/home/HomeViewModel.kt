package net.chineseguide.jukuu.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.chineseguide.jukuu.domain.usecase.GetResultListUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getResultListUseCase: GetResultListUseCase) : ViewModel() {

    private val _state = MutableLiveData<HomeState>()
    val state: LiveData<HomeState> = _state

    init {
        updateState()
    }

    private fun updateState() {
        val taskList = getResultListUseCase()

        _state.value = if (taskList.isEmpty()) {
            HomeState.EmptyTaskList
        } else {
            HomeState.TaskList(taskList)
        }
    }
}