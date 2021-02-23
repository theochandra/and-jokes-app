package com.android.chucknorrisjokesapp.di.application

import com.android.data.api.ServiceApi
import com.android.data.mapper.JokeMapper
import com.android.data.repository.JokeRepositoryImpl
import com.android.domain.repository.JokeRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideJokeRepository(
        serviceApi: ServiceApi,
        mapper: JokeMapper
    ): JokeRepository {
        return JokeRepositoryImpl(serviceApi, mapper)
    }

}