package net.chineseguide.jukuu.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val resultId: String
) : ViewModel() {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _details = MutableLiveData<String>()
    val details: LiveData<String> = _details

    init {

    }
}