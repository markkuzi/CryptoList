package com.example.cryptolist.presentation

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cryptolist.databinding.CoinsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class Coins : Fragment() {

    private var binding: CoinsBinding? = null
    private val coinsMarketViewModel: CoinsMarketViewModel by viewModel()
    private val coinInfoViewModel: CoinInfoViewModel by viewModel()
    private var coinsAdapter: CoinsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = CoinsBinding.inflate(inflater, container, false)

        loadCategories()
        initRecyclerProducts()

        coinsMarketViewModel.loadCoinsMarket.observe(viewLifecycleOwner, Observer {
            coinInfoViewModel.getCoinInfo((context as Activity), it[1].name.lowercase())
        })

        return binding?.root
    }

    private fun initRecyclerProducts() {
        binding?.coinsList?.layoutManager = GridLayoutManager(context, 2)
        coinsAdapter = CoinsAdapter()
        binding?.coinsList?.adapter = coinsAdapter

    }

    private fun loadCategories() {
        coinInfoViewModel.loadCoinInfo.observe(viewLifecycleOwner, Observer {
            coinsAdapter?.setList(it)
            coinsAdapter?.notifyDataSetChanged()
        })


    }



}