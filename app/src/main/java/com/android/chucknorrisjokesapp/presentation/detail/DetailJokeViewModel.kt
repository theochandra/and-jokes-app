package com.android.chucknorrisjokesapp.presentation.detail

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.chucknorrisjokesapp.presentation.mapper.JokeVmMapper
import com.android.chucknorrisjokesapp.presentation.model.JokeVM
import com.android.domain.Result
import com.android.domain.model.Joke
import com.android.domain.usecase.GetRandomJokeByCategoryUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailJokeViewModel @Inject constructor(
    private val useCase: GetRandomJokeByCategoryUseCase,
    private val mapper: JokeVmMapper
) : ViewModel() {

    private val _joke = MutableLiveData<JokeVM>()
    val joke: LiveData<JokeVM>
        get() = _joke

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _exception = MutableLiveData<Exception>()
    val exception: LiveData<Exception>
        get() = _exception

    val isLoading = ObservableBoolean()

    private fun changeLoadingState(state: Boolean) {
        isLoading.set(state)
    }

    fun getRandomJokeByCategory(category: String) {
        changeLoadingState(true)
        viewModelScope.launch {
            when (val result = useCase.execute(category)) {
                is Result.Success -> {
                    _joke.postValue(mapper.map(result.data))
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