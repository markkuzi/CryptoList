package com.example.cryptolist.domain.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.cryptolist.data.models.CoinInfoModel
import com.example.cryptolist.data.models.CoinsMarketModel

interface CoinsMarketCall {

    suspend fun getCoinsMarket(context: Context, vs_currency:String, pullRefresh: Boolean)

    fun loadCoinsMarket(): LiveData<List<CoinsMarketModel>>

    fun getProgressBarStatus() : LiveData<Boolean>

    fun getErrorStatus() : LiveData<Boolean>

}