package com.shahin.cryptoviewer.di.datasource

import com.shahin.cryptoviewer.datasource.repository.CurrencyRepository
import com.shahin.cryptoviewer.datasource.repository.DefaultCurrencyRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module(includes = [NetworkModule::class])
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindCurrencyRepository(repository: DefaultCurrencyRepository): CurrencyRepository

}