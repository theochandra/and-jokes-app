package com.android.chucknorrisjokesapp.di.presentation.splash

import com.android.chucknorrisjokesapp.presentation.splash.SplashViewModelFactory
import com.android.domain.usecase.GetJokeCategoriesUseCase
import dagger.Module
import dagger.Provides

@Module
class SplashModule {

    @SplashScope
    @Provides
    fun provideSplashViewModelFactory(
        useCase: GetJokeCategoriesUseCase
    ) : SplashViewModelFactory {
        return SplashViewModelFactory(useCase)
    }

}