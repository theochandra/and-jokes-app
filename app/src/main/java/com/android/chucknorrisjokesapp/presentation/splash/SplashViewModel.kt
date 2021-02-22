package com.android.chucknorrisjokesapp.presentation.splash

import androidx.lifecycle.ViewModel
import com.android.domain.usecase.GetJokeCategoriesUseCase
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val useCase: GetJokeCategoriesUseCase
) : ViewModel() {
}