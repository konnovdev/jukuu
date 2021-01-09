package net.chineseguide.jukuu.data.repository

import androidx.core.net.toUri
import net.chineseguide.jukuu.data.converter.JukuuHtmlConverter
import net.chineseguide.jukuu.data.converter.UrlConverter
import net.chineseguide.jukuu.data.datasource.SentenceRemoteDataSource
import net.chineseguide.jukuu.domain.entity.SentenceCollection
import javax.inject.Inject

interface SentenceRepository {

    fun get(query: String): SentenceCollection

    fun getNext(query: String, sentenceIndex: Int): SentenceCollection?
}

class SentenceRepositoryImpl @Inject constructor(
    private val remoteDataSource: SentenceRemoteDataSource,
    private val urlConverter: UrlConverter,
    private val jukuuHtmlConverter: JukuuHtmlConverter
) : SentenceRepository {

    private companion object {
        const val JUKUU_SENTENCES_PER_PAGE = 10
        const val JUKUU_PAGES_LIMIT = 10
        val DOWNLOAD_NOT_NEEDED = null
    }

    private var lastDownloadedPage = 0

    override fun get(query: String): SentenceCollection =
        urlConverter.convert("${query.toUri()}")
            .let { remoteDataSource.getFirstPage(query = it) }
            .let(jukuuHtmlConverter::convert)
            .also { lastDownloadedPage = 0 }

    override fun getNext(query: String, sentenceIndex: Int): SentenceCollection? {
        val page = sentenceIndex / JUKUU_SENTENCES_PER_PAGE

        if (page <= lastDownloadedPage || page >= JUKUU_PAGES_LIMIT) {
            return DOWNLOAD_NOT_NEEDED
        }

        lastDownloadedPage++

        return urlConverter.convert("${query.toUri()}")
            .let { remoteDataSource.getCustomPage(query = it, page = page) }
            .let(jukuuHtmlConverter::convert)
    }
}