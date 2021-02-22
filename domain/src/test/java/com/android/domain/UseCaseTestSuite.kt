package com.android.domain

import com.android.domain.usecase.GetJokeCategoriesUseCaseTest
import com.android.domain.usecase.GetJokesByQueryUseCaseTest
import com.android.domain.usecase.GetRandomJokeByCategoryUseCaseTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    GetJokeCategoriesUseCaseTest::class,
    GetJokesByQueryUseCaseTest::class,
    GetRandomJokeByCategoryUseCaseTest::class,
)
class UseCaseTestSuite