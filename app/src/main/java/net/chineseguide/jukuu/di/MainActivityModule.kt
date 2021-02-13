package net.chineseguide.jukuu.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import net.chineseguide.jukuu.navigation.Navigator
import net.chineseguide.jukuu.navigation.JetpackNavigator

@Module
@InstallIn(ActivityComponent::class)
class MainActivityModule {

    @Provides
    fun provideJetpackNavigator(@ActivityContext activity: Context): Navigator =
        JetpackNavigator(activity as AppCompatActivity)
}