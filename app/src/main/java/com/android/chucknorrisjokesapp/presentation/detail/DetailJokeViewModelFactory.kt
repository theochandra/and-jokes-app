package com.android.chucknorrisjokesapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.chucknorrisjokesapp.presentation.mapper.JokeVmMapper
import com.android.domain.usecase.GetRandomJokeByCategoryUseCase

class DetailJokeViewModelFactory(
    private val useCase: GetRandomJokeByCategoryUseCase,
    private val mapper: JokeVmMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailJokeViewModel(useCase, mapper) as T
    }

}