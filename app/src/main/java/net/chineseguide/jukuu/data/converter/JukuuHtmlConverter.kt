package net.chineseguide.jukuu.data.converter

import net.chineseguide.jukuu.domain.entity.Sentence
import net.chineseguide.jukuu.domain.entity.SentenceCollection
import org.jsoup.nodes.Document
import java.util.*
import javax.inject.Inject

class JukuuHtmlConverter @Inject constructor() {

    fun convert(doc: Document): SentenceCollection = parse(doc)

    private fun parse(doc: Document): SentenceCollection {
        val sentences = mutableListOf<Sentence>()

        val headword = doc.head().getElementsByAttribute("title")
        val englishSentences = doc.body().getElementsByClass("e")
        val chineseSentences = doc.body().getElementsByClass("c")

        for (sentenceId in 0 until englishSentences.size) {
            val sentence = Sentence(
                sentenceId.toString(),
                englishSentences[sentenceId].html().removeTags().removeIndexNumber().removeLeadingSpace(),
                chineseSentences[sentenceId].html().removeTags().removeLeadingSpace()
            )
            sentences.add(sentence)
        }
        return SentenceCollection(UUID.randomUUID().toString(), headword.html(), sentences)
    }

    private fun String.removeTags(): String {
        val innerHtml = StringBuilder(this)
        val numberOfTags = innerHtml.count { it == '<' }

        for (i in 0..numberOfTags) {
            val openingTagIndex = innerHtml.indexOf('<')
            if (openingTagIndex != -1) {
                val closingTagIndex = innerHtml.indexOf('>')
                innerHtml.delete(openingTagIndex, closingTagIndex + 1)
            }
        }
        return innerHtml.toString().replace("\n", "")
    }

    private fun String.removeIndexNumber(): String = this.substringAfter(". ")

    private fun String.removeLeadingSpace(): String = this.removePrefix(" ")
}