package net.chineseguide.jukuu.data

import net.chineseguide.jukuu.domain.entity.Result
import net.chineseguide.jukuu.domain.repository.ResultRepository
import javax.inject.Inject

class ResultRepositoryImpl @Inject constructor() : ResultRepository {

    private val resultList = ArrayList<Result>()

    override fun getList(): List<Result> =
        resultList

    override fun getById(id: String): Result =
        resultList.first { it.id == id }
}