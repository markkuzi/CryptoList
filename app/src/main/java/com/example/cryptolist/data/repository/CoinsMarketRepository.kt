package com.example.cryptolist.data.repository

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cryptolist.data.api.ApiClient
import com.example.cryptolist.data.models.CoinInfoModel
import com.example.cryptolist.data.models.CoinsMarketModel
import com.example.cryptolist.domain.repository.CoinsMarketCall
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinsMarketRepository: CoinsMarketCall {
    private val _coinsMarket = MutableLiveData<List<CoinsMarketModel>>()
    private val coinsMarket: LiveData<List<CoinsMarketModel>> = _coinsMarket
    private var _progressBar = MutableLiveData<Boolean>()
    private val progressBar : LiveData<Boolean> = _progressBar
    private var _errStatus = MutableLiveData<Boolean>()
    private val errStatus : LiveData<Boolean> = _errStatus



    override suspend fun getCoinsMarket(context: Context, vs_currency:String, pullRefresh: Boolean) {

        if (!pullRefresh){
            _progressBar.postValue(true)
            _errStatus.postValue(false)
        }


        val call = ApiClient.instance?.api?.getCoinMarket(vs_currency)

        call?.enqueue(object: Callback<ArrayList<CoinsMarketModel>> {
            override fun onResponse(
                call: Call<ArrayList<CoinsMarketModel>>,
                response: Response<ArrayList<CoinsMarketModel>>
            ) {
                Log.d("RetrofitLogs", " ${response.body()?.get(0)?.name?.lowercase()}OnResponse Success ${call.toString()}")
                _coinsMarket.postValue(response.body())
                if (!pullRefresh){
                    _progressBar.postValue(false)
                }

            }

            override fun onFailure(call: Call<ArrayList<CoinsMarketModel>>, t: Throwable) {
                Log.d("RetrofitLogs", "OnFailure ${t.message}")
                if (!pullRefresh){
                    _progressBar.postValue(false)
                    _errStatus.postValue(true)
                }
                if (pullRefresh){
                    Toast.makeText(context, "Произошла ошибка при загрузке", Toast.LENGTH_SHORT).show()

                }

            }

        })

    }


    override fun loadCoinsMarket(): LiveData<List<CoinsMarketModel>> {return coinsMarket}

    override fun getProgressBarStatus(): LiveData<Boolean> {
        return progressBar
    }

    override fun getErrorStatus(): LiveData<Boolean> {
        return errStatus
    }
}

