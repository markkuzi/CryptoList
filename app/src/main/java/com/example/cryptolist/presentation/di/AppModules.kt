package com.example.cryptolist.presentation.di

import com.example.cryptolist.data.repository.CoinInfoRepository
import com.example.cryptolist.data.repository.CoinsMarketRepository
import com.example.cryptolist.domain.repository.CoinInfoCall
import com.example.cryptolist.domain.repository.CoinsMarketCall
import com.example.cryptolist.domain.useCase.CoinInfoUseCase
import com.example.cryptolist.domain.useCase.CoinsMarketUseCase
import com.example.cryptolist.presentation.CoinInfoViewModel
import com.example.cryptolist.presentation.CoinsMarketViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

    val moduleCoinsMarket = module {


        single<CoinsMarketCall> {CoinsMarketRepository()}

        single {CoinsMarketUseCase(get())}

        viewModel { CoinsMarketViewModel(get()) }
    }

val moduleCoinInfo = module {


    single<CoinInfoCall> {CoinInfoRepository()}

    single {CoinInfoUseCase(get())}

    viewModel { CoinInfoViewModel(get()) }
}
