package com.android.chucknorrisjokesapp.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.domain.usecase.GetJokeCategoriesUseCase

class SplashViewModelFactory(
    private val useCase: GetJokeCategoriesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SplashViewModel(useCase) as T
    }

}