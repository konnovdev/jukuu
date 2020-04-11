package net.chineseguide.jukuu.domain.entity

import java.io.Serializable
import java.util.*

data class Sentence(
    val id: String,
    val originalSentence: String,
    val translatedSentence: String
) : Serializable