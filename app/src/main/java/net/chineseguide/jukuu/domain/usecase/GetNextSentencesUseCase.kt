package net.chineseguide.jukuu.domain.usecase

import net.chineseguide.jukuu.data.repository.SentenceRepository
import javax.inject.Inject

class GetNextSentencesUseCase @Inject constructor(
    private val sentenceRepository: SentenceRepository
) {

    operator fun invoke(query: String, page: Int) =
        sentenceRepository.get(query, page)
}