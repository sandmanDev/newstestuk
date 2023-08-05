package com.haroon.newstestuk.data.api

import com.haroon.newstestuk.data.model.Coin
import com.haroon.newstestuk.data.model.CoinDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApiService {
    @GET("v1/coins")
    suspend fun getCoins(): List<Coin>

    @GET("v1/coins/{id}")
    suspend fun getCoinById(@Path("id") id: String): CoinDetails
}
