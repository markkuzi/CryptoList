package com.example.cryptolist.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptolist.domain.useCase.CoinInfoUseCase
import com.example.cryptolist.domain.useCase.CoinsMarketUseCase
import kotlinx.coroutines.launch

class CoinInfoViewModel(private val coinInfoUseCase: CoinInfoUseCase): ViewModel() {

    fun getCoinInfo(context: Context, nameCoin:String) = viewModelScope.launch {
        coinInfoUseCase.getCoinInfo(context, nameCoin)
    }

    val loadCoinInfo = coinInfoUseCase.loadCoinInfo()

    val progressBarStatus = coinInfoUseCase.getProgressBarStatus()

    val errorStatus = coinInfoUseCase.getErrorStatus()

}