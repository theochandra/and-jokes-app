package com.android.domain.usecase

import com.android.domain.Result
import com.android.domain.coroutines.CoroutineTestRule
import com.android.domain.model.JokeList
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
class GetJokesByQueryUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Mock
    lateinit var repository: JokeRepository

    private lateinit var sut: GetJokesByQueryUseCase

    @Before
    fun setup() {
        sut = GetJokesByQueryUseCase(repository)
    }

    @Test
    fun `returns nothing when loading`() {
        runBlocking {
            given(repository.getJokesByQuery(any()))
                .willReturn(Result.Loading)

            sut.execute("test")

            verify(repository).getJokesByQuery(any())
        }
    }

    @Test
    fun `returns list joke when success retrieving data`() {
        runBlocking {
            val jokes: JokeList = mock()
            given(repository.getJokesByQuery(any()))
                .willReturn(Result.Success(jokes))

            sut.execute("test")

            verify(repository).getJokesByQuery(any())
        }
    }

    @Test
    fun `returns error when failed retrieving data`() {
        runBlocking {
            given(repository.getJokesByQuery(any()))
                .willReturn(Result.Error(404, "error occurred"))

            sut.execute("test")

            verify(repository).getJokesByQuery(any())
        }
    }

    @Test
    fun `returns exception when exception occurred`() {
        runBlocking {
            val exception: Exception = mock()
            given(repository.getJokesByQuery(any()))
                .willReturn(Result.Exception(exception))

            sut.execute("test")

            verify(repository).getJokesByQuery(any())
        }
    }

}