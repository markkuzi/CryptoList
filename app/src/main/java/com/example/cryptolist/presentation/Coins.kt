package com.example.cryptolist.presentation

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptolist.R
import com.example.cryptolist.databinding.CoinsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class Coins : Fragment() {

    private var binding: CoinsBinding? = null
    private val coinsMarketViewModel: CoinsMarketViewModel by viewModel()
    private val coinInfoViewModel: CoinInfoViewModel by viewModel()
    private var coinsAdapter: CoinsAdapter? = null
    private var getPreferences: SharedPreferences? = null
    private var currency: String = "usd"
    private var currencyToAdapter: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = CoinsBinding.inflate(inflater, container, false)

        getPreferences = (context as Activity).getSharedPreferences(
            "SaveData",
            Context.MODE_PRIVATE
        )

        loadCoins()
        initRecyclerCoins()
        progressBarStatus()
        errStatus()
        pullToRefresh()
        changeCurrency()


        return binding?.root
    }

    private fun initRecyclerCoins() {
        binding?.coinsList?.layoutManager = LinearLayoutManager(context)
        coinsAdapter = CoinsAdapter({ name: String ->
            showCoinInfo(
                name
            )
        })
        binding?.coinsList?.adapter = coinsAdapter

    }

    private fun loadCoins() {
        coinsMarketViewModel.loadCoinsMarket.observe(viewLifecycleOwner, Observer {
            coinsAdapter?.setList(it)
            coinsAdapter?.getCurrency(currencyToAdapter.toString())
            coinsAdapter?.notifyDataSetChanged()
        })
    }

    private fun showCoinInfo(name: String) {

        val editor = getPreferences?.edit()

        editor?.putString("name", name)
        editor?.apply()
        parentFragmentManager.beginTransaction().replace(R.id.content, CoinInfo()).addToBackStack(null).commit()

    }

    private fun progressBarStatus(){
        coinsMarketViewModel.progressBarStatus.observe(viewLifecycleOwner, Observer {
            if (it){
                binding?.progressBar?.show()
                binding?.coinsList?.visibility = View.GONE
            }
            else
            {binding?.progressBar?.hide()
            binding?.coinsList?.visibility = View.VISIBLE}
        })
    }


    private fun errStatus(){

        binding?.btnErr?.setOnClickListener(View.OnClickListener {
            coinsMarketViewModel.getCoinsMarket((context as Activity), currency, false)
        })

        coinsMarketViewModel.errorStatus.observe(viewLifecycleOwner, Observer {
            if (it){
                binding?.btnErr?.visibility = View.VISIBLE
                binding?.imageErr?.visibility = View.VISIBLE
                binding?.textErr?.visibility = View.VISIBLE
                binding?.pullToRefresh?.visibility = View.GONE
            }
            else{
                binding?.btnErr?.visibility = View.GONE
                binding?.imageErr?.visibility = View.GONE
                binding?.textErr?.visibility = View.GONE
                binding?.pullToRefresh?.visibility = View.VISIBLE
            }
        })
    }

    private fun pullToRefresh(){
        binding?.pullToRefresh?.setOnRefreshListener() {
            binding?.pullToRefresh?.isRefreshing = false

            coinsMarketViewModel.getCoinsMarket((context as Activity), currency, true)
            loadCoins()

        }

    }

    private fun  changeCurrency(){
        currencyToAdapter = getString(R.string.usd_currency)

        binding?.usd?.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked){
                currency = "usd"
                currencyToAdapter = getString(R.string.usd_currency)
                coinsMarketViewModel.getCoinsMarket((context as Activity), currency, false)
            }}

        binding?.eur?.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked){
                currency = "eur"
                currencyToAdapter = getString(R.string.eur_currency)
                coinsMarketViewModel.getCoinsMarket((context as Activity), currency, false)
            }}

    }

}

