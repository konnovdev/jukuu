package net.chineseguide.jukuu.domain.entity

data class SentenceCollection(
    val id: String,
    val headword: String,
    val sentences: MutableList<Sentence>
)