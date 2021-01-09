package net.chineseguide.jukuu.data.datasource

import net.chineseguide.jukuu.data.api.SentenceApi
import org.jsoup.nodes.Document
import javax.inject.Inject

interface SentenceRemoteDataSource {

    fun getFirstPage(query: String): Document

    fun getCustomPage(query: String, page: Int): Document
}

class SentenceRemoteDataSourceImpl @Inject constructor(
    private val sentenceApi: SentenceApi
) : SentenceRemoteDataSource {

    override fun getFirstPage(query: String): Document =
        sentenceApi.getFirstPage(query)

    override fun getCustomPage(query: String, page: Int): Document =
        sentenceApi.getCustomPage(query, page)
}