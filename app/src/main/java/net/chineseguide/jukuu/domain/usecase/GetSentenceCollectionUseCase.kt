package net.chineseguide.jukuu.domain.usecase

import net.chineseguide.jukuu.data.repository.SentenceRepository
import net.chineseguide.jukuu.domain.entity.Sentence
import javax.inject.Inject

class GetSentenceCollectionUseCase @Inject constructor(
    private val sentenceRepository: SentenceRepository
) {

    operator fun invoke(query: String): List<Sentence> =
        sentenceRepository.get(query)
}