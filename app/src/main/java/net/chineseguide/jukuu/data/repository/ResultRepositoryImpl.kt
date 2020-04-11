package net.chineseguide.jukuu.data.repository

import androidx.core.net.toUri
import net.chineseguide.jukuu.data.converter.JukuuHtmlConverter
import net.chineseguide.jukuu.data.converter.UrlConverter
import net.chineseguide.jukuu.data.datasource.ResultRemoteDataSource
import net.chineseguide.jukuu.domain.entity.Result
import net.chineseguide.jukuu.domain.repository.ResultRepository
import javax.inject.Inject

class ResultRepositoryImpl @Inject constructor(
    private val remoteDataSource: ResultRemoteDataSource,
    private val urlConverter: UrlConverter,
    private val jukuuHtmlConverter: JukuuHtmlConverter
) : ResultRepository {

    override fun getList(query: String): List<Result> =
        urlConverter.convert("www.jukuu.com/search.php?q=${query.toUri()}")
            .let(remoteDataSource::get)
            .let(jukuuHtmlConverter::convert)
}