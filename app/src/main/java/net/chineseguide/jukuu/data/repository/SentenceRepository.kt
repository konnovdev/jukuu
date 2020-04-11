package net.chineseguide.jukuu.data.repository

import androidx.core.net.toUri
import net.chineseguide.jukuu.data.converter.JukuuHtmlConverter
import net.chineseguide.jukuu.data.converter.UrlConverter
import net.chineseguide.jukuu.data.datasource.ResultRemoteDataSource
import net.chineseguide.jukuu.domain.entity.SentenceCollection
import javax.inject.Inject

interface SentenceRepository {

    fun get(query: String): SentenceCollection
}

class SentenceRepositoryImpl @Inject constructor(
    private val remoteDataSource: ResultRemoteDataSource,
    private val urlConverter: UrlConverter,
    private val jukuuHtmlConverter: JukuuHtmlConverter
) : SentenceRepository {

    override fun get(query: String): SentenceCollection =
        urlConverter.convert("${query.toUri()}")
            .let { remoteDataSource.get("http://www.jukuu.com/search.php?q=$it") }
            .let(jukuuHtmlConverter::convert)
}