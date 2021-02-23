package com.android.chucknorrisjokesapp.di

import com.android.chucknorrisjokesapp.di.presentation.detail.DetailSubComponent
import com.android.chucknorrisjokesapp.di.presentation.search.SearchSubComponent
import com.android.chucknorrisjokesapp.di.presentation.splash.SplashSubComponent

interface Injector {

    fun createSplashSubComponent(): SplashSubComponent

    fun createDetailSubComponent(): DetailSubComponent

    fun createSearchSubComponent(): SearchSubComponent

}