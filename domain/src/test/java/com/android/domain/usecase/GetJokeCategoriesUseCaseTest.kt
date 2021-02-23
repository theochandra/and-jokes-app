package com.android.domain.usecase

import com.android.domain.Result
import com.android.domain.coroutines.CoroutineTestRule
import com.android.domain.repository.JokeRepository
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
class GetJokeCategoriesUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Mock
    lateinit var repository: JokeRepository

    private lateinit var sut: GetJokeCategoriesUseCase

    @Before
    fun setup() {
        sut = GetJokeCategoriesUseCase(repository)
    }

    @Test
    fun `returns list categories when success retrieving data`() {
        runBlocking {
            val categories: List<String> = mock()
            given(repository.getJokeCategories())
                .willReturn(Result.Success(categories))

            sut.execute()

            verify(repository).getJokeCategories()
        }
    }

    @Test
    fun `returns error when failed retrieving data`() {
        runBlocking {
            given(repository.getJokeCategories())
                .willReturn(Result.Error(404, "error occurred"))

            sut.execute()

            verify(repository).getJokeCategories()
        }
    }

    @Test
    fun `returns exception when exception occurred`() {
        runBlocking {
            val exception: Exception = mock()
            given(repository.getJokeCategories())
                .willReturn(Result.Exception(exception))

            sut.execute()

            verify(repository).getJokeCategories()
        }
    }

}