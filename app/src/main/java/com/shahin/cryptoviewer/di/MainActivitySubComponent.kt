package com.shahin.cryptoviewer.di

import com.shahin.cryptoviewer.ui.list.CurrencyListFragment
import dagger.Subcomponent

@Subcomponent
interface MainActivitySubComponent {

    fun inject(currencyListFragment: CurrencyListFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainActivitySubComponent
    }

}