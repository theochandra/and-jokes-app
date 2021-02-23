package com.android.chucknorrisjokesapp.presentation.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.chucknorrisjokesapp.observeOnce
import com.android.chucknorrisjokesapp.presentation.mapper.JokeVmMapper
import com.android.chucknorrisjokesapp.repository.FakeJokeRepositoryImpl
import com.android.domain.Result
import com.android.domain.model.JokeList
import com.android.domain.usecase.GetJokesByQueryUseCase
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

class SearchJokeViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mapper: JokeVmMapper

    private lateinit var useCase: GetJokesByQueryUseCase

    private lateinit var sut: SearchJokeViewModel

    private val query = "test"

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        val fakeRepository = FakeJokeRepositoryImpl()
        useCase = GetJokesByQueryUseCase(fakeRepository)
        sut = SearchJokeViewModel(useCase, mapper)
    }

    @Test
    fun `observe joke list when get result success`() {
        var jokeList: JokeList = mock()
        runBlocking {
            when (val result = useCase.execute(query)) {
                is Result.Success -> {
                    jokeList = result.data
                }
            }
        }
        sut.jokeList.observeOnce { jokeVmList ->
            assertThat(jokeVmList, equalTo(jokeList.result.map { mapper.map(it) }))
        }
    }

    @Test
    fun `observe error when get result error`() {
        val errorCode = 404
        val errorMessage = "Error Occurred!"
        val useCase: GetJokesByQueryUseCase = mock()

        runBlocking {
            given(useCase.execute(query))
                .willReturn(Result.Error(errorCode, errorMessage))
        }
        sut.error.observeOnce { message ->
            assertThat(message, equalTo(errorMessage))
        }
    }

    @Test
    fun `observe exception when get result exception`() {
        val exception: Exception = mock()
        val useCase: GetJokesByQueryUseCase = mock()

        runBlocking {
            given(useCase.execute(query))
                .willReturn(Result.Exception(exception))
        }
        sut.exception.observeOnce { e ->
            assertThat(e, equalTo(exception))
        }
    }

}