package net.chineseguide.jukuu.domain.repository

import net.chineseguide.jukuu.domain.entity.Result

interface ResultRepository {

    fun getList(): List<Result>

    fun getById(id: String): Result
}