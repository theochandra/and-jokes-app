package com.android.data.mapper

import com.android.data.response.JokeListResponse
import com.android.data.response.JokeResponse
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class JokeMapperTest {

    private lateinit var sut: JokeMapper

    private lateinit var jokeResponse: JokeResponse
    private lateinit var jokeListResponse: JokeListResponse

    @Before
    fun setup() {
        sut = JokeMapper()

        jokeResponse = JokeResponse(
            categories = listOf(),
            createdAt = "",
            iconUrl = "",
            id = "",
            updatedAt = "",
            url = "",
            value = ""
        )

        jokeListResponse = JokeListResponse(
            total = 10,
            result = listOf(jokeResponse)
        )
    }

    @Test
    fun `maps categories`() {
        val result = sut.map(jokeResponse)
        assertThat(result.categories, equalTo(jokeResponse.categories))
    }

    @Test
    fun `maps created at`() {
        val result = sut.map(jokeResponse)
        assertThat(result.createdAt, equalTo(jokeResponse.createdAt))
    }

    @Test
    fun `maps icon url`() {
        val result = sut.map(jokeResponse)
        assertThat(result.url, equalTo(jokeResponse.url))
    }

    @Test
    fun `maps id`() {
        val result = sut.map(jokeResponse)
        assertThat(result.id, equalTo(jokeResponse.id))
    }

    @Test
    fun `maps updated at`() {
        val result = sut.map(jokeResponse)
        assertThat(result.updatedAt, equalTo(jokeResponse.updatedAt))
    }

    @Test
    fun `maps url`() {
        val result = sut.map(jokeResponse)
        assertThat(result.url, equalTo(jokeResponse.url))
    }

    @Test
    fun `maps value`() {
        val result = sut.map(jokeResponse)
        assertThat(result.value, equalTo(jokeResponse.value))
    }

    @Test
    fun `maps total`() {
        val result = sut.map(jokeListResponse)
        assertThat(result.total, equalTo(jokeListResponse.total))
    }

    @Test
    fun `maps result`() {
        val result = sut.map(jokeListResponse)
        assertThat(result.result, equalTo(jokeListResponse.result.map { sut.map(it) }))
    }

}