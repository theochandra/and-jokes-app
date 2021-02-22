package com.android.data.response

import com.google.gson.annotations.SerializedName

data class JokeListResponse(
    @SerializedName("total") val total: Int,
    @SerializedName("result") val result: List<JokeResponse>
)
