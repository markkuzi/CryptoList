package com.example.cryptolist.domain.useCase

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.cryptolist.data.models.CoinInfoModel
import com.example.cryptolist.data.models.CoinsMarketModel
import com.example.cryptolist.domain.repository.CoinInfoCall
import com.example.cryptolist.domain.repository.CoinsMarketCall

class CoinInfoUseCase (private val coinInfoCall: CoinInfoCall) {

    suspend fun getCoinInfo(context: Context, nameCoin:String){
        coinInfoCall.getCoinInfo(context, nameCoin)
    }

    fun loadCoinInfo(): LiveData<List<CoinInfoModel>> {
        return coinInfoCall.loadCoinInfo()
    }

    fun getProgressBarStatus():LiveData<Boolean>{
        return coinInfoCall.getProgressBarStatus()
    }

    fun getErrorStatus():LiveData<Boolean>{
        return coinInfoCall.getErrorStatus()
    }
}