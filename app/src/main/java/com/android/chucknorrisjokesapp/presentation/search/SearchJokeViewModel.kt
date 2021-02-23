package com.android.chucknorrisjokesapp.presentation.search

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.chucknorrisjokesapp.presentation.mapper.JokeVmMapper
import com.android.chucknorrisjokesapp.presentation.model.JokeVM
import com.android.domain.Result
import com.android.domain.usecase.GetJokesByQueryUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchJokeViewModel @Inject constructor(
    private val useCase: GetJokesByQueryUseCase,
    private val mapper: JokeVmMapper
) : ViewModel() {

    private val _jokeList = MutableLiveData<List<JokeVM>>()
    val jokeList: LiveData<List<JokeVM>>
        get() = _jokeList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _exception = MutableLiveData<Exception>()
    val exception: LiveData<Exception>
        get() = _exception

    val query = MutableLiveData<String>()
    fun getQuery(): LiveData<String> {
        return query
    }

    val isLoading = ObservableBoolean()

    fun changeLoadingState(state: Boolean) {
        isLoading.set(state)
    }

    fun getJokesByQuery(query: String) {
        changeLoadingState(true)
        viewModelScope.launch {
            when (val result = useCase.execute(query)) {
                is Result.Success -> {
                    _jokeList.postValue(result.data.result.map { mapper.map(it) })
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