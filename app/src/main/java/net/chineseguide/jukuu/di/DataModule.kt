package net.chineseguide.jukuu.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import net.chineseguide.jukuu.data.datasource.SentenceRemoteDataSource
import net.chineseguide.jukuu.data.datasource.SentenceRemoteDataSourceImpl
import net.chineseguide.jukuu.data.repository.SentenceRepository
import net.chineseguide.jukuu.data.repository.SentenceRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
interface DataModule {

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