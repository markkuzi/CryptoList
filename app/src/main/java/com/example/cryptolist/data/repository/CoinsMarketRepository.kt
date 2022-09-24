package com.example.cryptolist.data.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cryptolist.data.api.ApiClient
import com.example.cryptolist.data.models.CoinInfoModel
import com.example.cryptolist.data.models.CoinsMarketModel
import com.example.cryptolist.domain.repository.CoinsMarketCall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinsMarketRepository: CoinsMarketCall {
    private val _coinsMarket = MutableLiveData<List<CoinsMarketModel>>()
    private val coinsMarket: LiveData<List<CoinsMarketModel>> = _coinsMarket


    override suspend fun getCoinsMarket(context: Context, vs_currency:String) {


        val call = ApiClient.instance?.api?.getCoinMarket(vs_currency)

        call?.enqueue(object: Callback<ArrayList<CoinsMarketModel>> {
            override fun onResponse(
                call: Call<ArrayList<CoinsMarketModel>>,
                response: Response<ArrayList<CoinsMarketModel>>
            ) {
                Log.d("RetrofitLogs", " ${response.body()?.get(0)?.name?.lowercase()}OnResponse Success ${call.toString()}")

                _coinsMarket.postValue(response.body())

            }

            override fun onFailure(call: Call<ArrayList<CoinsMarketModel>>, t: Throwable) {
                Log.d("RetrofitLogs", "OnFailure ${t.message}")
            }

        })

    }


    override fun loadCoinsMarket(): LiveData<List<CoinsMarketModel>> {return coinsMarket}
}