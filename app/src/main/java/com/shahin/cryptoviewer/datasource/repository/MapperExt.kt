package com.shahin.cryptoviewer.datasource.repository

import com.shahin.cryptoviewer.datasource.model.Currency
import com.shahin.cryptoviewer.datasource.network.model.CurrencyDto

fun CurrencyDto.toDomain(): Currency =
    Currency(
        id = id,
        rank = rank,
        priceUsd = priceUsd,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        volume24 = volume24,
        percentChange1h = percentChange1h,
        percentChange24h = percentChange24h,
        percentChange7d = percentChange7d
    )