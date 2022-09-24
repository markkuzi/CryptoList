package com.example.cryptolist.presentation

import android.app.Application
import com.example.cryptolist.presentation.di.moduleCoinInfo
import com.example.cryptolist.presentation.di.moduleCoinsMarket
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Koin Android logger
            androidLogger()
            //inject Android context
            androidContext(this@App)

            modules(moduleCoinsMarket, moduleCoinInfo)

        }

    }


}