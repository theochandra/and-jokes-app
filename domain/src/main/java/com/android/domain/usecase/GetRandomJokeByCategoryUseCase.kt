package com.android.domain.usecase

import com.android.domain.Result
import com.android.domain.model.Joke
import com.android.domain.repository.JokeRepository
import javax.inject.Inject

class GetRandomJokeByCategoryUseCase @Inject constructor(
    private val repository: JokeRepository
) {

    suspend fun execute(category: String): Result<Joke> = repository.getRandomJokeByCategory(category)

}