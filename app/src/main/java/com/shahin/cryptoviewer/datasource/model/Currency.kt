package com.shahin.cryptoviewer.datasource.model

data class Currency(
    val id: String,
    val rank: Int,
    val priceUsd: String,
    val name: String,
    val symbol: String,
    val marketCapUsd: String,
    val volume24: Double,
    val percentChange1h: String,
    val percentChange24h: String,
    val percentChange7d: String,
)
