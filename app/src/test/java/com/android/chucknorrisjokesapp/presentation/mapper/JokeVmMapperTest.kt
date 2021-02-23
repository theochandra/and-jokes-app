package com.android.chucknorrisjokesapp.presentation.mapper

import com.android.domain.model.Joke
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class JokeVmMapperTest {

    private lateinit var sut: JokeVmMapper

    private lateinit var joke: Joke

    @Before
    fun setup() {
        sut = JokeVmMapper()

        joke = Joke(
            categories = listOf(),
            createdAt = "",
            iconUrl = "",
            id = "",
            updatedAt = "",
            url = "",
            value = ""
        )
    }

    @Test
    fun `maps value`() {
        val result = sut.map(joke)
        assertThat(result.value, equalTo(joke.value))
    }

    @Test
    fun `maps icon url`() {
        val result = sut.map(joke)
        assertThat(result.iconUrl, equalTo(joke.iconUrl))
    }

    @Test
    fun `maps updated at`() {
        val result = sut.map(joke)
        assertThat(result.updatedAt, equalTo(joke.updatedAt))
    }

}