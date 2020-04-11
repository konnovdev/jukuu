package net.chineseguide.jukuu.data.converter

import net.chineseguide.jukuu.domain.entity.Result
import org.jsoup.nodes.Document
import javax.inject.Inject

class JukuuHtmlConverter @Inject constructor() {

    // TODO write a converter
    fun convert(doc: Document): List<Result> {
        println("OUPUT HTML: $doc")

        return listOf()
    }
}