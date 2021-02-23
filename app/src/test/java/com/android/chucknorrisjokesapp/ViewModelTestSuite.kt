package com.android.chucknorrisjokesapp

import com.android.chucknorrisjokesapp.presentation.detail.DetailJokeViewModelTest
import com.android.chucknorrisjokesapp.presentation.mapper.JokeVmMapperTest
import com.android.chucknorrisjokesapp.presentation.search.SearchJokeViewModelTest
import com.android.chucknorrisjokesapp.presentation.splash.SplashViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    DetailJokeViewModelTest::class,
    SearchJokeViewModelTest::class,
    SplashViewModelTest::class,
    JokeVmMapperTest::class
)
class ViewModelTestSuite