package com.android.chucknorrisjokesapp.di.presentation.detail

import com.android.chucknorrisjokesapp.presentation.detail.DetailJokeViewModelFactory
import com.android.chucknorrisjokesapp.presentation.mapper.JokeVmMapper
import com.android.domain.usecase.GetRandomJokeByCategoryUseCase
import dagger.Module
import dagger.Provides

@Module
class DetailModule {

    @DetailScope
    @Provides
    fun provideDetailJokeViewModelFactory(
        useCase: GetRandomJokeByCategoryUseCase,
        mapper: JokeVmMapper
    ) : DetailJokeViewModelFactory {
        return DetailJokeViewModelFactory(useCase, mapper)
    }

}