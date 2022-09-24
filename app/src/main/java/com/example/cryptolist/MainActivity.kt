package com.example.cryptolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import com.example.cryptolist.databinding.ActivityMainBinding
import com.example.cryptolist.presentation.Coins
import com.example.cryptolist.presentation.CoinsMarketViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val coinsMarketViewModel: CoinsMarketViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)


        coinsMarketViewModel.getCoinsMarket(this, "usd")

        supportFragmentManager.beginTransaction().replace(R.id.content, Coins()).commit()






    }
}