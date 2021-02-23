package com.android.domain.usecase

import com.android.domain.Result
import com.android.domain.coroutines.CoroutineTestRule
import com.android.domain.model.Joke
import com.android.domain.repository.JokeRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetRandomJokeByCategoryUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Mock
    lateinit var repository: JokeRepository

    private lateinit var sut: GetRandomJokeByCategoryUseCase

    @Before
    fun setup() {
        sut = GetRandomJokeByCategoryUseCase(repository)
    }

    @Test
    fun `returns joke when success retrieving data`() {
        runBlocking {
            val joke: Joke = mock()
            given(repository.getRandomJokeByCategory(any()))
                .willReturn(Result.Success(joke))

            sut.execute("animal")

            verify(repository).getRandomJokeByCategory(any())
        }
    }

    @Test
    fun `returns error when failed retrieving data`() {
        runBlocking {
            given(repository.getRandomJokeByCategory(any()))
                .willReturn(Result.Error(404, "error occurred"))

            sut.execute("animal")

            verify(repository).getRandomJokeByCategory(any())
        }
    }

    @Test
    fun `returns exception when exception occurred`() {
        runBlocking {
            val exception: Exception = mock()
            given(repository.getRandomJokeByCategory(any()))
                .willReturn(Result.Exception(exception))

            sut.execute("animal")

            verify(repository).getRandomJokeByCategory(any())
        }
    }

}