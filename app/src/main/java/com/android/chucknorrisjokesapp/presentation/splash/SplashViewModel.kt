package com.android.chucknorrisjokesapp.presentation.splash

import androidx.databinding.ObservableBoolean
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

    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>>
        get() = _categories

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _exception = MutableLiveData<Exception>()
    val exception: LiveData<Exception>
        get() = _exception

    val isLoading = ObservableBoolean()

    init {
        getJokeCategories()
    }

    private fun changeLoadingState(state: Boolean) {
        isLoading.set(state)
    }

    private fun getJokeCategories() {
        changeLoadingState(true)
        viewModelScope.launch {
            when (val result = useCase.execute()) {
                is Result.Success -> {
                    _categories.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.errorMessage)
                }
                is Result.Exception -> {
                    _exception.postValue(result.exception)
                }
            }
            changeLoadingState(false)
        }
    }

}