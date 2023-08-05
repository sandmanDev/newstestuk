package com.haroon.newstestuk.data.repository

import com.haroon.newstestuk.data.api.CoinApiService
import com.haroon.newstestuk.data.model.Coin
import com.haroon.newstestuk.data.model.CoinDetails
import javax.inject.Inject


class CoinRepositoryImpl @Inject constructor(private val coinApiService: CoinApiService) :
    CoinRepository {
    override suspend fun getCoins(): List<Coin> = coinApiService.getCoins()

    override suspend fun getCoinById(id: String): CoinDetails = coinApiService.getCoinById(id)
}

