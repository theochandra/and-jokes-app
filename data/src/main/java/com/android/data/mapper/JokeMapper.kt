package com.android.data.mapper

import com.android.data.response.JokeListResponse
import com.android.data.response.JokeResponse
import com.android.domain.model.Joke
import com.android.domain.model.JokeList
import javax.inject.Inject

class JokeMapper @Inject constructor() {

    fun map(response: JokeResponse): Joke {
        return Joke(
            categories = response.categories,
            createdAt = response.createdAt,
            iconUrl = response.iconUrl,
            id = response.id,
            updatedAt = response.updatedAt,
            url = response.url,
            value = response.value
        )
    }

    fun map(response: JokeListResponse): JokeList {
        return JokeList(
            total = response.total,
            result = response.result.map { map(it) }
        )
    }

}