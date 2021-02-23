package com.android.chucknorrisjokesapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.chucknorrisjokesapp.presentation.mapper.JokeVmMapper
import com.android.domain.usecase.GetJokesByQueryUseCase

class SearchJokeViewModelFactory(
    private val useCase: GetJokesByQueryUseCase,
    private val mapper: JokeVmMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchJokeViewModel(useCase, mapper) as T
    }

}