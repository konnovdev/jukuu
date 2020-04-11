package net.chineseguide.jukuu.domain.entity

import java.io.Serializable
import java.util.*

data class Result(
    val id: String,
    val title: String,
    val details: String
) : Serializable