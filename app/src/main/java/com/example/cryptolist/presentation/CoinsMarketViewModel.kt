package com.example.cryptolist.presentation

import android.content.Context
import androidx.lifecycle.*
import com.example.cryptolist.domain.useCase.CoinsMarketUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoinsMarketViewModel(private val coinsMarketUseCase: CoinsMarketUseCase): ViewModel() {

    fun getCoinsMarket(context: Context, vs_currency:String, pullRefresh: Boolean) = viewModelScope.launch {
        coinsMarketUseCase.getCoinsMarket(context, vs_currency, pullRefresh)
    }

    val loadCoinsMarket = coinsMarketUseCase.loadCoinsMarket()

    val progressBarStatus = coinsMarketUseCase.getProgressBarStatus()

    val errorStatus = coinsMarketUseCase.getErrorStatus()


}

