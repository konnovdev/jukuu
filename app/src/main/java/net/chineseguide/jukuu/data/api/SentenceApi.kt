package net.chineseguide.jukuu.data.api

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject

interface SentenceApi {

    fun getFirstPage(query: String): Document

    fun getCustomPage(query: String, page: Int): Document
}

class SentenceApiImpl @Inject constructor() : SentenceApi {

    private companion object {

        const val FIRST_PAGE_URL = "http://www.jukuu.com/search.php?q="
        const val CUSTOM_PAGE_URL = "http://www.jukuu.com/show-"
    }

    override fun getFirstPage(query: String): Document =
        Jsoup.connect(FIRST_PAGE_URL + query).get()

    override fun getCustomPage(query: String, page: Int): Document =
        Jsoup.connect("$CUSTOM_PAGE_URL$query-$page.html").get()
}
