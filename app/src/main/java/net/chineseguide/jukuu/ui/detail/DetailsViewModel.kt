package net.chineseguide.jukuu.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.chineseguide.jukuu.domain.usecase.GetResultByIdUseCase
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val resultId: String,
    private val getResultByIdUseCase: GetResultByIdUseCase
) : ViewModel() {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _details = MutableLiveData<String>()
    val details: LiveData<String> = _details

    init {
        val task = getResultByIdUseCase(resultId)
        _title.value = task.title
        _details.value = task.details
    }
}