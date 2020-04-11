package net.chineseguide.jukuu.data.converter

import net.chineseguide.jukuu.domain.entity.Sentence
import net.chineseguide.jukuu.domain.entity.SentenceCollection
import org.jsoup.nodes.Document
import javax.inject.Inject

class JukuuHtmlConverter @Inject constructor() {

    // TODO write a converter
    fun convert(doc: Document): SentenceCollection {
        println("OUPUT HTML: $doc")

        //just for test
        return SentenceCollection(id = "232312",
            headword = "hello",
            sentences = listOf(
                Sentence("232", "hello everyone", "大家好"),
                Sentence("232", "hello freind", "你好朋友")
            ))
    }
}