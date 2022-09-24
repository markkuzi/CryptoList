package com.example.cryptolist.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfoModel(
    @SerializedName("categories")
    @Expose
    val categories: List<String>,

    @SerializedName("description")
    @Expose
    val description: Description

)

data class Description(
    val en: String
)

