package com.stockbit.model.network

import com.google.gson.annotations.SerializedName

data class CryptoResponse(
    @SerializedName("Type")
    val type: Int? = null,
    @SerializedName("Message")
    val message: String? = null,
    @SerializedName("MetaData")
    val metaData: MetaData? = null,
    @SerializedName("HasWarning")
    val hasWarning: Boolean? = null,
    @SerializedName("Data")
    val data: List<DataItem>? = null,
)

data class DataItem(
    @SerializedName("DISPLAY")
    val display: Display? = null,

    @SerializedName("CoinInfo")
    val coinInfo: CoinInfo? = null
)

data class CoinInfo(
    @SerializedName("Id")
    val id: Int? = null,

    @SerializedName("Name")
    val name: String? = null,

    @SerializedName("FullName")
    val fullName: String? = null,
)


data class MetaData(
    @SerializedName("Count")
    val count: Int? = null
)


data class Display(
    @SerializedName("USD")
    val usd: USD? = null
)

data class USD(
    @SerializedName("PRICE")
    val price: String? = null,

    @SerializedName("CHANGE24HOUR")
    val changeHourValue: String? = null,

    @SerializedName("CHANGEPCT24HOUR")
    val changePercentageHour: Double? = null
)