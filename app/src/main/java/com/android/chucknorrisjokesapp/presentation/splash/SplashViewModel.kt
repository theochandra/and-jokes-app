package com.android.chucknorrisjokesapp.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.Result
import com.android.domain.usecase.GetJokeCategoriesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val useCase: GetJokeCategoriesUseCase
) : ViewModel() {

    private val _stateData = MutableLiveData<Result<List<String>>>()
    val stateData: LiveData<Result<List<String>>>
        get() = _stateData

    init {
        getJokeCategories()
    }

    private fun getJokeCategories() {
        viewModelScope.launch {
            _stateData.value = Result.Loading
            val result = useCase.execute()
            _stateData.value = result
        }
    }

}