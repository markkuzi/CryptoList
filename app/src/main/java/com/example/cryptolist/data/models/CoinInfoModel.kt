package com.example.cryptolist.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfoModel(
    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("categories")
    @Expose
    val categories: List<String>,

    @SerializedName("description")
    @Expose
    val description: Description,

    @SerializedName("image")
    @Expose
    val image: Image

)

data class Description(
    val en: String
)

data class Image(
    val thumb: String,
    val small: String,
    val large: String
)

