package com.example.cryptolist.data.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cryptolist.data.api.ApiClient
import com.example.cryptolist.data.models.CoinInfoModel
import com.example.cryptolist.domain.repository.CoinInfoCall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinInfoRepository:CoinInfoCall {


    private val _coinsInfo = MutableLiveData<List<CoinInfoModel>>()
    private val coinsInfo: LiveData<List<CoinInfoModel>> = _coinsInfo
    private var _progressBar = MutableLiveData<Boolean>()
    private val progressBar : LiveData<Boolean> = _progressBar
    private var _errStatus = MutableLiveData<Boolean>()
    private val errStatus : LiveData<Boolean> = _errStatus

    override suspend fun getCoinInfo(context: Context, nameCoin:String) {
        _progressBar.postValue(true)
        _errStatus.postValue(false)

        val call = ApiClient.instance?.api?.getCoinInfo(nameCoin)

        call?.enqueue(object: Callback<CoinInfoModel> {
            override fun onResponse(call: Call<CoinInfoModel>, response: Response<CoinInfoModel>) {

                _coinsInfo.postValue(listOf(CoinInfoModel(response.body()!!.name, response.body()!!.categories ,response.body()!!.description, response.body()!!.image)))
                _progressBar.postValue(false)
                Log.d("RetrofitLogs", "${response.body().toString()}OnResponse Success ${call.toString()}")
            }

            override fun onFailure(call: Call<CoinInfoModel>, t: Throwable) {
                Log.d("RetrofitLogs", "OnFailure ${t.message}")
                _progressBar.postValue(false)
                _errStatus.postValue(true)
            }

        })

    }

    override fun loadCoinInfo(): LiveData<List<CoinInfoModel>> {return coinsInfo}

    override fun getProgressBarStatus(): LiveData<Boolean> {
        return progressBar
    }

    override fun getErrorStatus(): LiveData<Boolean> {
        return errStatus
    }
}