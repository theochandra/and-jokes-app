package com.android.chucknorrisjokesapp.di.application

import com.android.chucknorrisjokesapp.di.presentation.detail.DetailSubComponent
import com.android.chucknorrisjokesapp.di.presentation.splash.SplashSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    RepositoryModule::class,
    NetModule::class
])
interface AppComponent {

    fun splashSubComponent(): SplashSubComponent.Factory

    fun detailSubComponent(): DetailSubComponent.Factory

}