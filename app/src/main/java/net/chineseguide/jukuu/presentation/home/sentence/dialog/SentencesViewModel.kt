package net.chineseguide.jukuu.presentation.home.sentence.dialog

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import net.chineseguide.jukuu.domain.entity.Sentence
import java.lang.IllegalArgumentException

class SentencesViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _clipboardText = MutableLiveData<String>()
    val clipboardText: LiveData<String> = _clipboardText

    private val sentence: Sentence = savedStateHandle.get<Sentence>("sentence_arg")
        ?: throw IllegalArgumentException("Sentence is null in SentencesViewModel")

    fun copyOriginalText() {
        _clipboardText.value = sentence.originalSentence
    }

    fun copyTranslatedText() {
        _clipboardText.value = sentence.translatedSentence
    }

    fun copyBoth() {
        _clipboardText.value = "${sentence.originalSentence}\n${sentence.translatedSentence}"
    }
}