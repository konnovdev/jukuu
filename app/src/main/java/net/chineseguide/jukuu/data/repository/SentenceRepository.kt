package net.chineseguide.jukuu.data.repository

import net.chineseguide.jukuu.data.converter.JukuuHtmlConverter
import net.chineseguide.jukuu.data.converter.UrlConverter
import net.chineseguide.jukuu.data.datasource.SentenceRemoteDataSource
import net.chineseguide.jukuu.domain.entity.Sentence
import javax.inject.Inject

interface SentenceRepository {

    fun get(query: String): List<Sentence>

    fun getNext(query: String, sentenceIndex: Int): List<Sentence>
}

class SentenceRepositoryImpl @Inject constructor(
    private val remoteDataSource: SentenceRemoteDataSource,
    private val urlConverter: UrlConverter,
    private val jukuuHtmlConverter: JukuuHtmlConverter
) : SentenceRepository {

    private companion object {
        const val JUKUU_SENTENCES_PER_PAGE = 10
        const val JUKUU_PAGES_LIMIT = 10
    }

    private var lastDownloadedPage = 0

    override fun get(query: String): List<Sentence> =
        urlConverter.convert(query)
            .let { remoteDataSource.getFirstPage(query = it) }
            .let(jukuuHtmlConverter::convert)
            .also { lastDownloadedPage = 0 }

    override fun getNext(query: String, sentenceIndex: Int): List<Sentence> {
        val page = sentenceIndex / JUKUU_SENTENCES_PER_PAGE

        if (page <= lastDownloadedPage || page >= JUKUU_PAGES_LIMIT) {
            return listOf()
        }

        lastDownloadedPage++

        return urlConverter.convert(query)
            .let { remoteDataSource.getCustomPage(query = it, page = page) }
            .let(jukuuHtmlConverter::convert)
    }
}