package net.chineseguide.jukuu.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import net.chineseguide.jukuu.presentation.ErrorLogger
import net.chineseguide.jukuu.presentation.ErrorLoggerImpl
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
interface LoggerModule {

    @Singleton
    @Binds
    fun bindErrorLogger(
        logger: ErrorLoggerImpl
    ): ErrorLogger
}