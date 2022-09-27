package com.example.cryptolist.domain.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.cryptolist.data.models.CoinInfoModel
import com.example.cryptolist.data.models.CoinsMarketModel

interface CoinInfoCall {

    suspend fun getCoinInfo(context: Context, nameCoin:String)

    fun loadCoinInfo(): LiveData<List<CoinInfoModel>>

    fun getProgressBarStatus() : LiveData<Boolean>

    fun getErrorStatus() : LiveData<Boolean>

}