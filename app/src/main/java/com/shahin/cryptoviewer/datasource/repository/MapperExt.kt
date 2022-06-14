package com.shahin.cryptoviewer.datasource.repository

import com.shahin.cryptoviewer.datasource.model.Currency
import com.shahin.cryptoviewer.datasource.network.model.CurrencyDto

fun CurrencyDto.toDomain(): Currency =
    Currency(
        id = id,
        rank = rank,
        priceUsd = String.format("%.4f",quoteDto.usdQuoteDto.priceUsd),
        name = name,
        symbol = symbol,
        marketCapUsd = quoteDto.usdQuoteDto.marketCapUsd.toString(),
        volume24 = quoteDto.usdQuoteDto.volume24,
        percentChange1h = quoteDto.usdQuoteDto.percentChange1h,
        percentChange24h = quoteDto.usdQuoteDto.percentChange24h,
        percentChange7d = quoteDto.usdQuoteDto.percentChange7d
    )