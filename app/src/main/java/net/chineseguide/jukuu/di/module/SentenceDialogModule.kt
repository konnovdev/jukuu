package net.chineseguide.jukuu.di.module

import net.chineseguide.jukuu.domain.entity.Sentence
import toothpick.config.Module

class SentenceDialogModule(sentence: Sentence) : Module() {

    init {
        bind(Sentence::class.java)
            .toInstance(sentence)
    }
}