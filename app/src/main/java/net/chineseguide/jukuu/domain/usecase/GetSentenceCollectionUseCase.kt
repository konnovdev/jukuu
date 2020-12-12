package net.chineseguide.jukuu.domain.usecase

import net.chineseguide.jukuu.data.repository.SentenceRepository
import net.chineseguide.jukuu.domain.entity.SentenceCollection
import javax.inject.Inject

class GetSentenceCollectionUseCase @Inject constructor(
    private val sentenceRepository: SentenceRepository
) {

    operator fun invoke(query: String): SentenceCollection =
        sentenceRepository.get(query)
}