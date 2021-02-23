package com.android.chucknorrisjokesapp.presentation.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.chucknorrisjokesapp.observeOnce
import com.android.chucknorrisjokesapp.repository.FakeJokeRepositoryImpl
import com.android.domain.Result
import com.android.domain.usecase.GetJokeCategoriesUseCase
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class SplashViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var useCase: GetJokeCategoriesUseCase

    private lateinit var sut: SplashViewModel

    @Before
    fun setup() {
        val fakeRepository = FakeJokeRepositoryImpl()
        useCase = GetJokeCategoriesUseCase(fakeRepository)
        sut = SplashViewModel(useCase)
    }

    @Test
    fun `observe categories when get result success`() {
        var categories = listOf<String>()
        runBlocking {
            when (val result = useCase.execute()) {
                is Result.Success -> {
                    categories = result.data
                }
            }
        }
        sut.categories.observeOnce {
            assertThat(it, equalTo(categories))
        }
    }

    @Test
    fun `observe error when get result error`() {
        val errorCode = 404
        val errorMessage = "Error Occurred!"
        val useCase: GetJokeCategoriesUseCase = mock()

        runBlocking {
            given(useCase.execute())
                .willReturn(Result.Error(errorCode, errorMessage))
        }
        sut.error.observeOnce { message ->
            assertThat(message, equalTo(errorMessage))
        }
    }

    @Test
    fun `observe exception when get result exception`() {
        val exception: Exception = mock()
        val useCase: GetJokeCategoriesUseCase = mock()

        runBlocking {
            given(useCase.execute())
                    .willReturn(Result.Exception(exception))
        }
        sut.exception.observeOnce { e ->
            assertThat(e, equalTo(exception))
        }
    }

}