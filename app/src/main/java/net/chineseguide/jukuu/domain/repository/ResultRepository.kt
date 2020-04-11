package net.chineseguide.jukuu.domain.repository

import net.chineseguide.jukuu.domain.entity.Result

interface ResultRepository {

    fun getList(query: String): List<Result>
}