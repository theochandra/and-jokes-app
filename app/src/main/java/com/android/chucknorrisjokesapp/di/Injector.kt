package com.android.chucknorrisjokesapp.di

import com.android.chucknorrisjokesapp.di.presentation.splash.SplashSubComponent

interface Injector {

    fun createSplashSubComponent(): SplashSubComponent

}