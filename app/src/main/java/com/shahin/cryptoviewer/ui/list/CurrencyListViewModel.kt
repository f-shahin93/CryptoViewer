package com.shahin.cryptoviewer.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahin.cryptoviewer.datasource.model.Currency
import com.shahin.cryptoviewer.datasource.model.DataResult
import com.shahin.cryptoviewer.datasource.repository.CurrencyRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrencyListViewModel @Inject constructor(
    currencyRepository: CurrencyRepository
) : ViewModel() {

    val currencySuccess = MutableLiveData<List<Currency>>()
    val currencyLoading = MutableLiveData<Boolean>()
    val currencyError = MutableLiveData<Throwable>()

    init {
        viewModelScope.launch {
            currencyRepository.getCurrencyList().collect { result ->
                when (result) {
                    is DataResult.Success -> {
                        currencySuccess.postValue(result.data ?: emptyList())
                    }
                    is DataResult.Loading -> {
                        currencyLoading.postValue(result.data == null)
                    }
                    is DataResult.Error -> {
                        currencyError.postValue(result.message ?: Throwable())
                    }
                }
            }
        }
    }

}