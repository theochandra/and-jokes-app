package com.android.chucknorrisjokesapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.Result
import com.android.domain.model.Joke
import com.android.domain.usecase.GetRandomJokeByCategoryUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailJokeViewModel @Inject constructor(
    private val useCase: GetRandomJokeByCategoryUseCase
) : ViewModel() {

    private val _stateData = MutableLiveData<Result<Joke>>()
    val stateData: LiveData<Result<Joke>>
        get() = _stateData

    fun getRandomJokeByCategory(category: String) {
        viewModelScope.launch {
            _stateData.value = Result.Loading
            val result = useCase.execute(category)
            _stateData.value = result
        }
    }

}