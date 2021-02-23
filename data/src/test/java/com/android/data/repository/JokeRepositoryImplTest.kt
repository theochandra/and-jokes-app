package com.android.data.repository

import com.android.data.api.ServiceApi
import com.android.data.mapper.JokeMapper
import com.android.data.response.JokeListResponse
import com.android.data.response.JokeResponse
import com.android.domain.Result
import com.android.domain.model.Joke
import com.android.domain.model.JokeList
import com.android.domain.repository.JokeRepository
import com.nhaarman.mockito_kotlin.*
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response.success

@RunWith(MockitoJUnitRunner::class)
class JokeRepositoryImplTest {

    @Mock
    lateinit var serviceApi: ServiceApi
    @Mock
    lateinit var mapper: JokeMapper

    private lateinit var sut: JokeRepository

    private val query = "test"
    private val category = "animal"

    @Before
    fun setup() {
        sut = JokeRepositoryImpl(serviceApi, mapper)
    }

    @Test
    fun `gets joke categories`() {
        runBlocking {
            given(serviceApi.getJokeCategories()).willReturn(mock())
            sut.getJokeCategories()
            verify(serviceApi).getJokeCategories()
        }
    }

    @Test
    fun `gets jokes by query`() {
        runBlocking {
            given(serviceApi.getJokesByQuery(any())).willReturn(mock())
            sut.getJokesByQuery(query)
            verify(serviceApi).getJokesByQuery(any())
        }
    }

    @Test
    fun `maps jokes by query`() {
        runBlocking {
            val jokeListResponse = mock<JokeListResponse>()
            val response = success(jokeListResponse)

            given(serviceApi.getJokesByQuery(any())).willReturn(response)
            given(mapper.map(jokeListResponse)).willReturn(mock())
            sut.getJokesByQuery(query)
            verify(mapper).map(eq(jokeListResponse))
        }
    }

    @Test
    fun `returns mapped jokes by query`() {
        runBlocking {
            val jokeListResponse = mock<JokeListResponse>()
            val response = success(jokeListResponse)
            val mappedResponse = mock<JokeList>()

            given(serviceApi.getJokesByQuery(any())).willReturn(response)
            given(mapper.map(jokeListResponse)).willReturn(mappedResponse)
            val result = sut.getJokesByQuery(query)
            assertThat(Result.Success(mappedResponse), equalTo(result))
        }
    }

    @Test
    fun `gets random joke by category`() {
        runBlocking {
            given(serviceApi.getRandomJokeByCategory(any())).willReturn(mock())
            sut.getRandomJokeByCategory(category)
            verify(serviceApi).getRandomJokeByCategory(any())
        }
    }

    @Test
    fun `maps random joke by category`() {
        runBlocking {
            val jokeResponse = mock<JokeResponse>()
            val response = success(jokeResponse)

            given(serviceApi.getRandomJokeByCategory(any())).willReturn(response)
            given(mapper.map(jokeResponse)).willReturn(mock())
            sut.getRandomJokeByCategory(category)
            verify(mapper).map(eq(jokeResponse))
        }
    }

    @Test
    fun `returns mapped random joke by query`() {
        runBlocking {
            val jokeResponse = mock<JokeResponse>()
            val response = success(jokeResponse)
            val mappedResponse = mock<Joke>()

            given(serviceApi.getRandomJokeByCategory(any())).willReturn(response)
            given(mapper.map(jokeResponse)).willReturn(mappedResponse)
            val result = sut.getRandomJokeByCategory(category)
            assertThat(Result.Success(mappedResponse), equalTo(result))
        }
    }

}