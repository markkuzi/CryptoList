package com.example.cryptolist.domain.useCase

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.cryptolist.data.models.CoinInfoModel
import com.example.cryptolist.data.models.CoinsMarketModel
import com.example.cryptolist.domain.repository.CoinsMarketCall

class CoinsMarketUseCase(private val coinsMarketCall: CoinsMarketCall) {

    suspend fun getCoinsMarket(context: Context, vs_currency:String) {
        coinsMarketCall.getCoinsMarket(context, vs_currency)
    }

    fun loadCoinsMarket(): LiveData<List<CoinsMarketModel>>{
        return coinsMarketCall.loadCoinsMarket()
    }

}