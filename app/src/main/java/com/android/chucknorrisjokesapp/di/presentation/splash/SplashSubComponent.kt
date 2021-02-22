package com.android.chucknorrisjokesapp.di.presentation.splash

import com.android.chucknorrisjokesapp.presentation.splash.SplashActivity
import dagger.Subcomponent

@SplashScope
@Subcomponent (modules = [SplashModule::class])
interface SplashSubComponent {

    fun inject(splashActivity: SplashActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): SplashSubComponent
    }

}