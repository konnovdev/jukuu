package net.chineseguide.jukuu.data.datasource

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject

interface ResultRemoteDataSource {

    fun get(url: String): Document
}

class ResultRemoteDataSourceImpl @Inject constructor() : ResultRemoteDataSource {

    override fun get(url: String): Document =
        Jsoup.connect(url).get()
}