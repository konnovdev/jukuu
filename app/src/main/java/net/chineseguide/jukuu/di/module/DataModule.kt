package net.chineseguide.jukuu.di.module

import net.chineseguide.jukuu.data.datasource.ResultRemoteDataSource
import net.chineseguide.jukuu.data.datasource.ResultRemoteDataSourceImpl
import net.chineseguide.jukuu.data.repository.SentenceRepository
import net.chineseguide.jukuu.data.repository.SentenceRepositoryImpl
import toothpick.config.Module

class DataModule : Module() {

    init {
        bind(ResultRemoteDataSource::class.java)
            .to(ResultRemoteDataSourceImpl::class.java)

        bind(SentenceRepository::class.java)
            .to(SentenceRepositoryImpl::class.java)
            .singleton()
    }
}