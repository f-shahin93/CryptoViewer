package com.shahin.cryptoviewer.datasource.network.model

import com.google.gson.annotations.SerializedName

/*data class CurrencyDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("price_usd")
    val priceUsd: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("market_cap_usd")
    val marketCapUsd: String,
    @SerializedName("volume24")
    val volume24: Double,
    @SerializedName("percent_change_1h")
    val percentChange1h: String,
    @SerializedName("percent_change_24h")
    val percentChange24h: String,
    @SerializedName("percent_change_7d")
    val percentChange7d: String,
)*/

data class CurrencyDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("cmc_rank")
    val rank: Int,
    @SerializedName("quote")
    val quoteDto: QuoteCurrencyDto,
)

data class QuoteCurrencyDto(
    @SerializedName("USD")
    val usdQuoteDto: UsdQuoteDto
)

data class UsdQuoteDto(
    @SerializedName("price")
    val priceUsd: Double,
    @SerializedName("volume_24h")
    val volume24: Double,
    @SerializedName("market_cap")
    val marketCapUsd: Double,
    @SerializedName("percent_change_1h")
    val percentChange1h: Double,
    @SerializedName("percent_change_24h")
    val percentChange24h: Double,
    @SerializedName("percent_change_7d")
    val percentChange7d: Double,
)

data class MainResponse(
    @SerializedName("data")
    val data: List<CurrencyDto>
)