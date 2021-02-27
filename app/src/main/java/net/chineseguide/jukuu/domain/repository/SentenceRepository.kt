package net.chineseguide.jukuu.domain.repository

import net.chineseguide.jukuu.domain.entity.Sentence

interface SentenceRepository {

    fun get(query: String): List<Sentence>

    fun getNext(query: String, sentenceIndex: Int): List<Sentence>
}
