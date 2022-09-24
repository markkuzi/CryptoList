package com.example.cryptolist.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptolist.data.models.CoinsMarketModel
import com.example.cryptolist.domain.useCase.CoinsMarketUseCase
import kotlinx.coroutines.launch

class CoinsMarketViewModel(private val coinsMarketUseCase: CoinsMarketUseCase): ViewModel() {

    fun getCoinsMarket(context: Context, vs_currency:String) = viewModelScope.launch {
        coinsMarketUseCase.getCoinsMarket(context, vs_currency)
    }

    val loadCoinsMarket = coinsMarketUseCase.loadCoinsMarket()

}