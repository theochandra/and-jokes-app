package com.android.chucknorrisjokesapp.di.presentation.search

import com.android.chucknorrisjokesapp.presentation.mapper.JokeVmMapper
import com.android.chucknorrisjokesapp.presentation.search.SearchJokeViewModelFactory
import com.android.domain.usecase.GetJokesByQueryUseCase
import dagger.Module
import dagger.Provides

@Module
class SearchModule {

    @SearchScope
    @Provides
    fun provideSearchJokeViewModelFactory(
        useCase: GetJokesByQueryUseCase,
        mapper: JokeVmMapper
    ) : SearchJokeViewModelFactory {
        return SearchJokeViewModelFactory(useCase, mapper)
    }

}