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

    override suspend fun getCoinInfo(context: Context, nameCoin:String) {


        val call = ApiClient.instance?.api?.getCoinInfo(nameCoin)

        call?.enqueue(object: Callback<CoinInfoModel> {
            override fun onResponse(call: Call<CoinInfoModel>, response: Response<CoinInfoModel>) {

                _coinsInfo.postValue(listOf(CoinInfoModel(response.body()!!.categories ,response.body()!!.description)))

                Log.d("RetrofitLogs", "${response.body().toString()}OnResponse Success ${call.toString()}")

                Toast.makeText(context, "ЗАГРУЗКА", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<CoinInfoModel>, t: Throwable) {
                Log.d("RetrofitLogs", "OnFailure ${t.message}")
                Toast.makeText(context, "ОШИБКА! ВКЛЮЧИТЕ ИНТЕРНЕТ!${t.message}", Toast.LENGTH_SHORT).show()
            }

        })

    }

    override fun loadCoinInfo(): LiveData<List<CoinInfoModel>> {return coinsInfo}
}