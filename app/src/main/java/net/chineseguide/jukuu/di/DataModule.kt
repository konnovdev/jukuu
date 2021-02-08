package net.chineseguide.jukuu.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import net.chineseguide.jukuu.data.api.SentenceApi
import net.chineseguide.jukuu.data.api.SentenceApiImpl
import net.chineseguide.jukuu.data.datasource.SentenceRemoteDataSource
import net.chineseguide.jukuu.data.datasource.SentenceRemoteDataSourceImpl
import net.chineseguide.jukuu.data.repository.SentenceRepositoryImpl
import net.chineseguide.jukuu.domain.repository.SentenceRepository
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindSentenceApi(
        api: SentenceApiImpl
    ): SentenceApi

    @Singleton
    @Binds
    fun bindSentenceRemoteDataSource(
        dataSource: SentenceRemoteDataSourceImpl
    ): SentenceRemoteDataSource

    @Singleton
    @Binds
    fun bindSentenceRepository(
        repository: SentenceRepositoryImpl
    ): SentenceRepository
}