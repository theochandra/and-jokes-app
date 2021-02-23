package com.android.chucknorrisjokesapp.repository

import com.android.domain.Result
import com.android.domain.model.Joke
import com.android.domain.model.JokeList
import com.android.domain.repository.JokeRepository
import com.nhaarman.mockito_kotlin.mock

class FakeJokeRepositoryImpl : JokeRepository {

    override suspend fun getJokeCategories(): Result<List<String>> {
        val categoryList = listOf<String>()
        return Result.Success(categoryList)
    }

    override suspend fun getRandomJokeByCategory(category: String): Result<Joke> {
        val joke: Joke = mock()
        return Result.Success(joke)
    }

    override suspend fun getJokesByQuery(query: String): Result<JokeList> {
        val jokeList: JokeList = mock()
        return Result.Success(jokeList)
    }

}