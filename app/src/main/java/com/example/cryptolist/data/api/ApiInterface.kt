package com.example.cryptolist.data.api

import com.example.cryptolist.data.models.CoinInfoModel
import com.example.cryptolist.data.models.CoinsMarketModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("coins/markets")
    fun getCoinMarket(
        @Query("vs_currency") vs_currency: String,
        @Query("per_page") perPage: Int = 30
    ): Call<ArrayList<CoinsMarketModel>>

    @GET("coins/{id}")
    fun getCoinInfo(
        @Path("id") nameCoin: String,
        @Query("tickers") tickers: Boolean = false,
        @Query("market_data") market_data: Boolean = false,
        @Query("community_data") community_data: Boolean = false,
        @Query("developer_data") developer_data: Boolean = false,
        @Query("sparkline") sparkline: Boolean = false
    ): Call<CoinInfoModel>

}
