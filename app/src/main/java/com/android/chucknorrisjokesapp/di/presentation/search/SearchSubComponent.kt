package com.android.chucknorrisjokesapp.di.presentation.search

import com.android.chucknorrisjokesapp.presentation.search.SearchJokeActivity
import dagger.Subcomponent

@SearchScope
@Subcomponent(modules = [SearchModule::class])
interface SearchSubComponent {

    fun inject(searchJokeActivity: SearchJokeActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): SearchSubComponent
    }

}