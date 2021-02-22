package com.android.chucknorrisjokesapp.base

import android.app.Application
import com.android.chucknorrisjokesapp.di.Injector
import com.android.chucknorrisjokesapp.di.application.AppComponent
import com.android.chucknorrisjokesapp.di.application.AppModule
import com.android.chucknorrisjokesapp.di.application.DaggerAppComponent
import com.android.chucknorrisjokesapp.di.presentation.splash.SplashSubComponent

class BaseApplication : Application(), Injector {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .build()
    }

    override fun createSplashSubComponent(): SplashSubComponent {
        return appComponent.splashSubComponent().create()
    }

}