package net.chineseguide.jukuu.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import net.chineseguide.jukuu.navigation.NavigatorHolder
import net.chineseguide.jukuu.navigation.Router
import net.chineseguide.jukuu.navigation.RouterImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
interface MainViewModelModule {

    @Binds
    fun bindRouter(router: RouterImpl): Router

    @Binds
    fun bindNavigatorHolder(router: RouterImpl): NavigatorHolder
}