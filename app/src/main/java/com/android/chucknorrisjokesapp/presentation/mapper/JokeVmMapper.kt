package com.android.chucknorrisjokesapp.presentation.mapper

import com.android.chucknorrisjokesapp.presentation.model.JokeVM
import com.android.domain.model.Joke
import javax.inject.Inject

class JokeVmMapper @Inject constructor() {

    fun map(joke: Joke): JokeVM {
        return JokeVM(
            value = joke.value,
            iconUrl = joke.iconUrl,
            updatedAt = joke.updatedAt
        )
    }

}