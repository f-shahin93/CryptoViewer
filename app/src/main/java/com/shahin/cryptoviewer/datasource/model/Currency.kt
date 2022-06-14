package com.shahin.cryptoviewer.datasource.model

data class Currency(
    val id: Long,
    val rank: Int,
    val priceUsd: String,
    val name: String,
    val symbol: String,
    val marketCapUsd: String,
    val volume24: Double,
    val percentChange1h: Double,
    val percentChange24h: Double,
    val percentChange7d: Double,
)
