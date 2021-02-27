package net.chineseguide.jukuu.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import net.chineseguide.jukuu.ui.App
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
interface AppModule {

    companion object {

        @Singleton
        @Provides
        fun provideApplication(@ApplicationContext app: Context): App = app as App
    }
}