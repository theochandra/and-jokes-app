package com.android.domain.usecase

import com.android.domain.Result
import com.android.domain.repository.JokeRepository
import javax.inject.Inject

class GetJokeCategoriesUseCase @Inject constructor(
    private val repository: JokeRepository
) {

    suspend fun execute(): Result<List<String>> = repository.getJokeCategories()

}