package com.android.data

import com.android.data.mapper.JokeMapperTest
import com.android.data.repository.JokeRepositoryImplTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    JokeMapperTest::class,
    JokeRepositoryImplTest::class
)
class DataTestSuite