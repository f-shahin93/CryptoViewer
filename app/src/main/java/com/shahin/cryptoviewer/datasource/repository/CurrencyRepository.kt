package com.shahin.cryptoviewer.datasource.repository

import com.shahin.cryptoviewer.datasource.model.Currency
import com.shahin.cryptoviewer.datasource.model.DataResult
import com.shahin.cryptoviewer.datasource.network.service.CurrencyService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


interface CurrencyRepository {
    fun getCurrencyList(): Flow<DataResult<List<Currency>>>
}

class DefaultCurrencyRepository @Inject constructor(
    private val currencyService:CurrencyService,
) : CurrencyRepository {

    override fun getCurrencyList(): Flow<DataResult<List<Currency>>> = flow {
        emit(DataResult.Loading())

        val remoteResult = currencyService.getCurrencyList().data
        val domainResult = remoteResult.map { it.toDomain() }.sortedBy { it.rank }

        emit(DataResult.Success(domainResult))

    }.catch { ex -> emit(DataResult.Error(ex)) }
        .flowOn(Dispatchers.IO)

}