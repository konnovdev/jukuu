package net.chineseguide.jukuu.domain.usecase

import net.chineseguide.jukuu.data.repository.SentenceRepository
import net.chineseguide.jukuu.domain.entity.Sentence
import javax.inject.Inject

class GetNextSentencesUseCase @Inject constructor(
    private val sentenceRepository: SentenceRepository
) {

    operator fun invoke(query: String, sentenceIndex: Int): List<Sentence> =
        sentenceRepository.getNext(query, sentenceIndex)
}