package com.example.cryptolist.domain.useCase

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.cryptolist.data.models.CoinInfoModel
import com.example.cryptolist.data.models.CoinsMarketModel
import com.example.cryptolist.domain.repository.CoinsMarketCall

class CoinsMarketUseCase(private val coinsMarketCall: CoinsMarketCall) {

    suspend fun getCoinsMarket(context: Context, vs_currency:String, pullRefresh: Boolean) {
        coinsMarketCall.getCoinsMarket(context, vs_currency, pullRefresh)
    }

    fun loadCoinsMarket():LiveData<List<CoinsMarketModel>>{
        return coinsMarketCall.loadCoinsMarket()
    }

    fun getProgressBarStatus():LiveData<Boolean>{
        return coinsMarketCall.getProgressBarStatus()
    }

    fun getErrorStatus():LiveData<Boolean>{
        return coinsMarketCall.getErrorStatus()
    }

}