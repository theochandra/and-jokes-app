package com.android.chucknorrisjokesapp.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.chucknorrisjokesapp.observeOnce
import com.android.chucknorrisjokesapp.presentation.mapper.JokeVmMapper
import com.android.chucknorrisjokesapp.presentation.search.SearchJokeViewModel
import com.android.chucknorrisjokesapp.repository.FakeJokeRepositoryImpl
import com.android.domain.Result
import com.android.domain.model.Joke
import com.android.domain.model.JokeList
import com.android.domain.usecase.GetJokesByQueryUseCase
import com.android.domain.usecase.GetRandomJokeByCategoryUseCase
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DetailJokeViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mapper: JokeVmMapper

    private lateinit var useCase: GetRandomJokeByCategoryUseCase

    private lateinit var sut: DetailJokeViewModel

    private val category = "test"

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        val fakeRepository = FakeJokeRepositoryImpl()
        useCase = GetRandomJokeByCategoryUseCase(fakeRepository)
        sut = DetailJokeViewModel(useCase, mapper)
    }

    @Test
    fun `observe joke when get result success`() {
        var joke: Joke = mock()
        runBlocking {
            when (val result = useCase.execute(category)) {
                is Result.Success -> {
                    joke = result.data
                }
            }
        }
        sut.joke.observeOnce { jokeVM ->
            assertThat(jokeVM, equalTo(mapper.map(joke)))
        }
    }

    @Test
    fun `observe error when get result error`() {
        val errorCode = 404
        val errorMessage = "Error Occurred!"
        val useCase: GetRandomJokeByCategoryUseCase = mock()

        runBlocking {
            given(useCase.execute(category))
                .willReturn(Result.Error(errorCode, errorMessage))
        }
        sut.error.observeOnce { message ->
            assertThat(message, equalTo(errorMessage))
        }
    }

    @Test
    fun `observe exception when get result exception`() {
        val exception: Exception = mock()
        val useCase: GetRandomJokeByCategoryUseCase = mock()

        runBlocking {
            given(useCase.execute(category))
                .willReturn(Result.Exception(exception))
        }
        sut.exception.observeOnce { e ->
            assertThat(e, equalTo(exception))
        }
    }

}