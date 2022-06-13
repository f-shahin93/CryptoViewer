package com.shahin.cryptoviewer.datasource.network.service

import com.shahin.cryptoviewer.datasource.network.model.MainResponse
import retrofit2.http.GET

interface CurrencyService {

    @GET("digital-money/")
    suspend fun getCurrencyList(): MainResponse

}