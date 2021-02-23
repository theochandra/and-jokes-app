package com.android.chucknorrisjokesapp.di.presentation.detail

import com.android.chucknorrisjokesapp.presentation.detail.DetailJokeActivity
import dagger.Subcomponent

@DetailScope
@Subcomponent(modules = [DetailModule::class])
interface DetailSubComponent {

    fun inject(detailJokeActivity: DetailJokeActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailSubComponent
    }

}