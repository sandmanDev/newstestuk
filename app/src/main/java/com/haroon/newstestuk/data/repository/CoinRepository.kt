package com.haroon.newstestuk.data.repository

import com.haroon.newstestuk.data.model.Coin
import com.haroon.newstestuk.data.model.CoinDetails

interface CoinRepository {
    suspend fun getCoins(): List<Coin>

    suspend fun getCoinById(id: String): CoinDetails
}