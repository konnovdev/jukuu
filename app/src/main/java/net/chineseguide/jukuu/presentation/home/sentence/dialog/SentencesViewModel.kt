package net.chineseguide.jukuu.presentation.home.sentence.dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.chineseguide.jukuu.domain.entity.Sentence
import javax.inject.Inject

class SentencesViewModel @Inject constructor(private val sentence: Sentence): ViewModel() {

    private val _clipboardText = MutableLiveData<String>()
    val clipboardText: LiveData<String> = _clipboardText

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