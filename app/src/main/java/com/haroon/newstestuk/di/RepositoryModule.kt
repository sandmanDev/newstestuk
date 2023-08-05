package com.haroon.newstestuk.di

import com.haroon.newstestuk.data.api.CoinApiService
import com.haroon.newstestuk.data.repository.CoinRepository
import com.haroon.newstestuk.data.repository.CoinRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideCoinRepository(coinApiService: CoinApiService) : CoinRepository {
        return CoinRepositoryImpl(coinApiService)
    }
}