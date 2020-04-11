package net.chineseguide.jukuu.di.module

import net.chineseguide.jukuu.data.datasource.ResultRemoteDataSource
import net.chineseguide.jukuu.data.datasource.ResultRemoteDataSourceImpl
import net.chineseguide.jukuu.data.repository.ResultRepositoryImpl
import net.chineseguide.jukuu.domain.repository.ResultRepository
import toothpick.config.Module

class DataModule : Module() {

    init {
        bind(ResultRemoteDataSource::class.java)
            .to(ResultRemoteDataSourceImpl::class.java)

        bind(ResultRepository::class.java)
            .to(ResultRepositoryImpl::class.java)
            .singleton()
    }
}