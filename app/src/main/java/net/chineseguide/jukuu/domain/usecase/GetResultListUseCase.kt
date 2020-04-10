package net.chineseguide.jukuu.domain.usecase

import net.chineseguide.jukuu.domain.entity.Result
import net.chineseguide.jukuu.domain.repository.ResultRepository
import javax.inject.Inject

class GetResultListUseCase @Inject constructor(private val resultRepository: ResultRepository) {

    operator fun invoke(): List<Result> =
        resultRepository.getList()
}