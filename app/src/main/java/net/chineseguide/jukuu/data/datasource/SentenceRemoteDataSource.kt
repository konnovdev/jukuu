package net.chineseguide.jukuu.data.datasource

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject

interface SentenceRemoteDataSource {

    fun get(url: String): Document
}

class SentenceRemoteDataSourceImpl @Inject constructor() : SentenceRemoteDataSource {

    override fun get(url: String): Document =
        Jsoup.connect(url).get()
}