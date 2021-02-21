package com.android.domain.usecase

import com.android.domain.Result
import com.android.domain.model.JokeList
import com.android.domain.repository.JokeRepository
import javax.inject.Inject

class GetJokesByQueryUseCase @Inject constructor(
    private val repository: JokeRepository
) {

    suspend fun execute(query: String): Result<JokeList> = repository.getJokesByQuery(query)

}