package com.shahin.cryptoviewer.ui.list

import android.widget.Filter
import android.widget.Filterable
import androidx.lifecycle.MediatorLiveData
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
) : ViewModel(), Filterable {

    val mainList = MediatorLiveData<List<Currency>>()
    val currencySuccess = MutableLiveData<List<Currency>>()
    val currencyLoading = MutableLiveData<Boolean>()
    val currencyError = MutableLiveData<Throwable>()

    val searchResultList = MutableLiveData<List<Currency>>()

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
        mainList.addSource(currencySuccess){
            mainList.value = it
        }
        mainList.addSource(searchResultList){
            mainList.value = it
        }
    }
    fun search(query: String) {
        searchQuery = if (query.trim().isEmpty()) "" else query
        this.filter.filter(query)
    }
    var searchQuery = ""
    override fun getFilter() =
        object : Filter() {
            var searchList = mutableListOf<Currency>()

            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                searchQuery = charString
                searchList = when {
                    charString.isEmpty() -> currencySuccess.value as MutableList<Currency>
                    else -> getSearchResults(charString)
                }
                val searchResults = FilterResults()
                searchResults.values = searchList
                return searchResults
            }

            override fun publishResults(
                charSequence: CharSequence,
                filterResults: FilterResults
            ) {
                searchList = when {
                    filterResults.values != null -> filterResults.values as MutableList<Currency>
                    currencySuccess.value.isNullOrEmpty() == false -> currencySuccess as MutableList<Currency>
                    else -> mutableListOf()
                }
                searchResultList.value = searchList
            }
        }

    private fun getSearchResults(charString: String): MutableList<Currency> =
        currencySuccess.value?.filter {
            it.name.lowercase().contains(charString.lowercase()) ||
                    it.symbol.lowercase().contains(charString.lowercase())
        } as MutableList<Currency>

}