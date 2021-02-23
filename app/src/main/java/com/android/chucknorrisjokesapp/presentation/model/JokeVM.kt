package com.android.chucknorrisjokesapp.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class JokeVM(
    val value: String,
    val iconUrl: String,
    val updatedAt: String
) : Parcelable
