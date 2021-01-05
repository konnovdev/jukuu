package net.chineseguide.jukuu.di.module

import net.chineseguide.jukuu.data.datasource.SentenceRemoteDataSource
import net.chineseguide.jukuu.data.datasource.SentenceRemoteDataSourceImpl
import net.chineseguide.jukuu.data.repository.SentenceRepository
import net.chineseguide.jukuu.data.repository.SentenceRepositoryImpl
import toothpick.config.Module

@Deprecated("no need for hilt")
class DataModule : Module() {

    init {
        bind(SentenceRemoteDataSource::class.java)
            .to(SentenceRemoteDataSourceImpl::class.java)

        bind(SentenceRepository::class.java)
            .to(SentenceRepositoryImpl::class.java)
            .singleton()
    }
}