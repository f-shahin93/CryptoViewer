package com.shahin.cryptoviewer.di

import com.shahin.cryptoviewer.ui.detail.CurrencyDetailDialogFragment
import com.shahin.cryptoviewer.ui.list.CurrencyListFragment
import dagger.Subcomponent

@Subcomponent
interface MainActivitySubComponent {

    fun inject(currencyListFragment: CurrencyListFragment)
    fun inject(currencyDetailDialogFragment: CurrencyDetailDialogFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainActivitySubComponent
    }

}